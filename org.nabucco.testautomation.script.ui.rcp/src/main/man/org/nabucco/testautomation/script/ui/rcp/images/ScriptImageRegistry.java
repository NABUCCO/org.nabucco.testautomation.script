/*
 * Copyright 2012 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nabucco.testautomation.script.ui.rcp.images;

/**
 * Global Registry of all component images.
 * <p>
 * Used to register / create component images with
 * {@link org.nabucco.framework.plugin.base.layout.ImageProvider}.
 * 
 * @author Marco Sussek, PRODYNA AG
 */
public enum ScriptImageRegistry {

	ICON_ACTION("icons/script/action.png", "/icons/action.png"),

	ICON_ASSERTION("icons/script/assertion.png", "/icons/assertion.png"),

	ICON_BREAK("icons/script/break.png", "/icons/break.png"),
	
	ICON_CONDITION("icons/script/condition.png", "/icons/condition.png"),
	
	ICON_DICTIONARY("icons/script/dictionary.png", "/icons/dictionary.png"),
	
	ICON_EXECUTE("icons/script/execute.png", "/icons/execute.png"),
	
	ICON_LABEL("icons/script/label.png", "/icons/label.png"),
	
	ICON_FUNCTION("icons/script/function.png", "/icons/function.png"),
	
	ICON_LOOP("icons/script/loop.png", "/icons/loop.png"),
	
	ICON_MESSAGE("icons/script/message.png", "/icons/message.png"),
	
	ICON_METADATA("icons/script/metadata.png", "/icons/metadata.png"),
	
	ICON_SCRIPT("icons/script/script.png", "/icons/script.png"),
    
    ICON_REUSED("icons/script/reused.png", "/icons/reused.png"),

	ICON_EMBEDDED("icons/script/embedded_script.png", "/icons/embedded_script.png"),
	
	ICON_PROPERTY_ACTION("icons/script/property_action.png", "/icons/property_action.png"),
	
	ICON_FOLDER("icons/script/folder.png", "icons/folder.png"),
	
	ICON_FIND("icons/script/find.png", "/icons/find.png");
	
	/**
	 * The unique symbolic name of the image used to identify the image in the
	 * <code>ImageProvider</code>.
	 */
	private String id;

	/**
	 * The physical path of the image within the component JAR.
	 */
	private String resourcePath;

	private ScriptImageRegistry(String id, String resourcePath) {
		this.id = id;
		this.resourcePath = resourcePath;
	}

	/**
	 * Gets the unique symbolic name of the image used to identify the image in
	 * the <code>ImageProvider</code>.
	 * 
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Gets the physical path of the image within the component JAR.
	 * 
	 * @return the resourcePath
	 */
	public String getResourcePath() {
		return this.resourcePath;
	}
}
