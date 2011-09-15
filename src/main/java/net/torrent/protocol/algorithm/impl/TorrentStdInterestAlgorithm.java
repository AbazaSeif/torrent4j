/*
 * Copyright 2011 Rogiel Josias Sulzbach
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.torrent.protocol.algorithm.impl;

import net.torrent.protocol.algorithm.TorrentInterestAlgorithm;
import net.torrent.protocol.algorithm.impl.TorrentStdAlgorithm.TorrentStdAlgorithmContext;
import net.torrent.protocol.peerwire.manager.TorrentManager;
import net.torrent.torrent.context.TorrentPeer;
import net.torrent.torrent.context.TorrentPeer.ChokingState;
import net.torrent.torrent.context.TorrentPeer.InterestState;
import net.torrent.torrent.piece.PieceSelector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Standard torrent interest algorithm
 * 
 * @author <a href="http://www.rogiel.com/">Rogiel Josias Sulzbach</a>
 */
public class TorrentStdInterestAlgorithm implements TorrentInterestAlgorithm {
	/**
	 * The logger instance
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * The torrent manager
	 */
	@SuppressWarnings("unused")
	private final TorrentManager manager;
	/**
	 * The algorithm context
	 */
	private final TorrentStdAlgorithmContext context;

	/**
	 * This selector is used to find the next piece to be downloaded. Parts are
	 * managed inside this algorithm.
	 */
	private final PieceSelector selector;

	/**
	 * Creates a new instance
	 * 
	 * @param manager
	 *            the manager
	 * @param selector
	 *            the piece selector
	 */
	public TorrentStdInterestAlgorithm(TorrentManager manager,
			TorrentStdAlgorithmContext context, PieceSelector selector) {
		this.manager = manager;
		this.context = context;
		this.selector = selector;
	}

	@Override
	public InterestState interested(TorrentPeer peer) {
		log.debug("Checking interest in peer {}", peer);
		if (context.downloadingPieces >= 10) {
			log.debug("Already downloading 10 or more pieces, no interest in {}", peer);
			return InterestState.UNINTERESTED;
		}

		int pieces = selector.countPieces(peer);
		if (pieces >= 5) {
			log.debug("Peer {} has {} interesting pieces", peer, pieces);
			return InterestState.INTERESTED;
		}
		log.debug("Peer {} does not have 5 or more pieces we dont have", peer);
		return InterestState.INTERESTED;
	}

	@Override
	public ChokingState choke(TorrentPeer peer) {
		log.debug("Never choke peer {}", peer);
		return ChokingState.UNCHOKED;
	}
}
