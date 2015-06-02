package visualiser;

import de.jreality.plugin.JRViewer;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.Viewer;
import de.jreality.tools.ClickWheelCameraZoomTool;
import de.jreality.util.SceneGraphUtility;

public class GenericCanvas {

	protected Viewer viewer;
	protected SceneGraphComponent world = SceneGraphUtility.createFullSceneGraphComponent("Crinkle");
	protected int maxStepsPerMutation;
	protected int historyIndex;

	public GenericCanvas(int maxStepsPerMutation) {
		super();
		this.maxStepsPerMutation = maxStepsPerMutation;
		JRViewer jrViewer = JRViewer.createJRViewer(world);
		jrViewer.startupLocal();
		viewer = jrViewer.getViewer();

		//setup the canvas to allow mouse wheel zoom
		viewer.getSceneRoot().addTool(new ClickWheelCameraZoomTool());
	}

	public Viewer getViewer() {
		return viewer;
	}

	public void setViewer(Viewer viewer) {
		this.viewer = viewer;
	}

	public int getHistroyIndex() {
		return historyIndex;
	}

}