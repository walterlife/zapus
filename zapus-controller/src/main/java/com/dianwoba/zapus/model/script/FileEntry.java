/* 
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package com.dianwoba.zapus.model.script;

import static com.dianwoba.zapus.common.util.CollectionUtils.newHashMap;
import static com.dianwoba.zapus.common.util.Preconditions.checkNotEmpty;

import com.dianwoba.zapus.common.util.PathUtils;
import com.dianwoba.zapus.model.ZapusUser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.Expose;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.NumberUtils;

public class FileEntry {

	private static final long serialVersionUID = -2422243194192027508L;

	@Expose
	private long fileSize;

	@Expose
	private String content;

	@Expose
	private Map<String, String> properties = new HashMap<String, String>();

	@Expose
	private String description;

	@Expose
	private String encoding;

	private byte[] contentBytes;

	@Expose
	private String path;

	@Expose
	private FileType fileType;

	@Expose
	private long revision;

	private long lastRevision;

	private ZapusUser createdUser;

	private Date createdDate;

	private Date lastModifiedDate;

	public String getPath() {
		return path;
	}

	public String getPathInShort() {
		return PathUtils.getShortPath(path);
	}

	public String getFileName() {
		return FilenameUtils.getName(checkNotEmpty(getPath()));
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}


	public byte[] getContentBytes() {
		return contentBytes;
	}

	public void setContentBytes(byte[] contentBytes) {
		this.fileSize = contentBytes.length;
		this.contentBytes = contentBytes;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.fileSize = content.length();
		this.content = content;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@SuppressWarnings("UnusedDeclaration")
	public boolean isEditable() {
		return getFileType().getFileCategory().isEditable();
	}

	public FileType getFileType() {
		if (fileType == null) {
			fileType = FileType.getFileTypeByExtension(FilenameUtils.getExtension(getPath()));
		}
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public long getRevision() {
		return revision;
	}

	public void setRevision(long revision) {
		this.revision = revision;
	}

	public Map<String, String> getProperties() {
		if (this.properties == null) {
			return newHashMap();
		}
		return this.properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public long getLastRevision() {
		return lastRevision;
	}

	public void setLastRevision(long lastRevision) {
		this.lastRevision = lastRevision;
	}

	public ZapusUser getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(ZapusUser createdUser) {
		this.createdUser = createdUser;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public static class FileEntrySerializer implements JsonSerializer<FileEntry> {
		@Override
		public JsonElement serialize(FileEntry fileEntry, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject root = new JsonObject();
			root.addProperty("path", FilenameUtils.separatorsToUnix(fileEntry.getPath()));
			root.addProperty("pathInShort", FilenameUtils.separatorsToUnix(fileEntry.getPathInShort()));
			root.addProperty("revision", fileEntry.getRevision());
			String validateKey = MapUtils.getString(fileEntry.getProperties(), "validated", "0");
			if (NumberUtils.isNumber(validateKey)) {
				root.addProperty("validated", NumberUtils.createInteger(validateKey));
			} else {
				root.addProperty("validated", 0);
			}
			return root;
		}
	}
}
