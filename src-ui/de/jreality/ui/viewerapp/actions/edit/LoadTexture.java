/**
 *
 * This file is part of jReality. jReality is open source software, made
 * available under a BSD license:
 *
 * Copyright (c) 2003-2006, jReality Group: Charles Gunn, Tim Hoffmann, Markus
 * Schmies, Steffen Weissmann.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * - Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * - Neither the name of jReality nor the names of its contributors nor the
 *   names of their associated organizations may be used to endorse or promote
 *   products derived from this software without specific prior written
 *   permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */


package de.jreality.ui.viewerapp.actions.edit;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import de.jreality.scene.Appearance;
import de.jreality.shader.CommonAttributes;
import de.jreality.shader.ImageData;
import de.jreality.shader.TextureUtility;
import de.jreality.ui.viewerapp.FileFilter;
import de.jreality.ui.viewerapp.FileLoaderDialog;
import de.jreality.ui.viewerapp.SelectionEvent;
import de.jreality.ui.viewerapp.SelectionManager;
import de.jreality.ui.viewerapp.actions.AbstractSelectionListenerAction;
import de.jreality.util.Input;


/**
 * Loads a texture for a selected SceneGraphComponent or Appearance.
 * 
 * @author msommer
 */
public class LoadTexture extends AbstractSelectionListenerAction {

	public LoadTexture(String name, SelectionManager sm, Component frame) {

		super(name, sm, frame);
		setShortDescription("Load texture");
	}


	public void actionPerformed(ActionEvent e) {

		File file = FileLoaderDialog.loadFile(parentComp, true, FileFilter.createImageReaderFilters());
		if (file == null) return;  //dialog cancelled

		ImageData img = null;
		try { img = ImageData.load(Input.getInput(file)); } 
		catch (IOException exc) {	exc.printStackTrace(); }

		//get appearance
		Appearance app = null;
		if (getSelection().isComponent()) {
			app = getSelection().asComponent().getAppearance();
			if (app==null) {
				app = new Appearance();
				getSelection().asComponent().setAppearance(app);
			}
		}
		else app = (Appearance) getSelection().asNode();

		//create texture
		TextureUtility.createTexture(app, CommonAttributes.POLYGON_SHADER, img, false);
	}


	@Override
	public boolean isEnabled(SelectionEvent e) {
		return (e.componentSelected() || e.appearanceSelected());
	}

}