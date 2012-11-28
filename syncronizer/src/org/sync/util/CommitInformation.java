/*****************************************************************************
    This file is part of Git-Starteam.

    Git-Starteam is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Git-Starteam is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Git-Starteam.  If not, see <http://www.gnu.org/licenses/>.
******************************************************************************/
package org.sync.util;

/**
 * Note: this class has a natural ordering that is inconsistent with equals.
 * @author Steve Tousignant <s.tousignant@gmail.com>
 *
 */
public class CommitInformation implements Comparable<CommitInformation> {

	private long time;
	private int uid;
	private String comment;
	private String path;

	public CommitInformation(long time, int uid, String comment, String path) {
		this.time = time;
		this.uid = uid;
		this.comment = comment;
		this.path = path;
	}
	
	public long getTime() {
		return time;
	}
	
	public int getUid() {
		return uid;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getComment() {
		return comment;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof CommitInformation) {
			CommitInformation info = (CommitInformation) obj;
			return uid == info.uid &&
					(comment.length() == 0 || info.comment.length() == 0 || info.comment.equalsIgnoreCase(comment));
		}
		return false;
	}
	
	@Override
	public int compareTo(CommitInformation o) {
		if(time == o.time) {
			if(uid == o.uid) {
				if(comment.length() == 0) {
					return path.compareTo(o.path);
				} else if (o.comment.length() == 0) {
					return path.compareTo(o.path);
				} else if (comment.equalsIgnoreCase(o.comment)) {
					return path.compareTo(o.path);
				}
				return comment.compareTo(o.comment);
			} else if (uid > o.uid) {
				return 1;
			}
			return -1;
		} else if (time > o.time) {
			return 1;
		}
		return -1;
	}

	
}
