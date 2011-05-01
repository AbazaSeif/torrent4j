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

import net.torrent.protocol.algorithm.TorrentPieceUploadAlgorithm;
import net.torrent.protocol.peerwire.manager.TorrentManager;
import net.torrent.torrent.TorrentPart;
import net.torrent.torrent.context.TorrentPeer;

/**
 * Standard torrent upload algorithm
 * 
 * @author <a href="http://www.rogiel.com/">Rogiel Josias Sulzbach</a>
 */
public class TorrentStdPieceUploadAlgorithm implements
		TorrentPieceUploadAlgorithm {
	@SuppressWarnings("unused")
	private final TorrentManager manager;

	public TorrentStdPieceUploadAlgorithm(TorrentManager manager) {
		this.manager = manager;
	}

	@Override
	public RequestAction request(TorrentPeer peer, TorrentPart part) {
		return RequestAction.NONE;
	}

	@Override
	public boolean cancel(TorrentPeer peer, TorrentPart part) {
		// TODO Auto-generated method stub
		return false;
	}
}