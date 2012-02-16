package iiit.vlabdemos.bouncingball;


import java.awt.BorderLayout;
import java.awt.Frame;

import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Hello {
  public static void main(String[] args) {
    Frame frame = new Frame();
    frame.setSize(640, 480);
    frame.setLayout(new BorderLayout());

    Canvas3D canvas = new Canvas3D(null);
    frame.add("Center", canvas);

    SimpleUniverse univ = new SimpleUniverse(canvas);
    univ.getViewingPlatform().setNominalViewingTransform();

    BranchGroup scene = createSceneGraph();
    scene.compile();
    univ.addBranchGraph(scene);

    frame.show();
  }

  private static BranchGroup createSceneGraph() {
    // Make a scene graph branch
    BranchGroup branch = new BranchGroup();

    // Make a changeable 3D transform
    TransformGroup trans = new TransformGroup();
    trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    branch.addChild(trans);

    // Make a shape
    ColorCube demo = new ColorCube(0.4);
    trans.addChild(demo);

    // Make a behavor to spin the shape
    Alpha spinAlpha = new Alpha(-1, 4000);
    RotationInterpolator spinner = new RotationInterpolator(spinAlpha,
        trans);
    spinner.setSchedulingBounds(new BoundingSphere(new Point3d(), 1000.0));
    trans.addChild(spinner);

    return branch;
  }
}