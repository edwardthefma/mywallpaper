package com.mydomain.wallpaper.mywallpaper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import rajawali.BaseObject3D;
import rajawali.animation.Animation3D;
import rajawali.animation.RotateAnimation3D;
import rajawali.animation.RotateAroundAnimation3D;
import rajawali.lights.PointLight;

import rajawali.math.Number3D;
import rajawali.math.Number3D.Axis;

import rajawali.parser.ObjParser;
import rajawali.renderer.RajawaliRenderer;
import android.content.Context;


public class Renderer extends RajawaliRenderer {
	private PointLight mLight;
	private BaseObject3D mObjectGroup;
    private Animation3D mCameraAnim, mLightAnim;

	public Renderer(Context context) {
		super(context);
	}

	public void initScene() {
		mLight = new PointLight();
		mLight.setPosition(0, 0, -4);
		mLight.setPower(3);
		mCamera.setLookAt(0, 0, 0);
		mCamera.setZ(-12);
		
		ObjParser objParser = new ObjParser(mContext.getResources(), mTextureManager, R.raw.multiobjects_obj);
		objParser.parse();
		mObjectGroup = objParser.getParsedObject();
		mObjectGroup.addLight(mLight);
		addChild(mObjectGroup);

		mCameraAnim = new RotateAnimation3D(Axis.Y, 360);
		mCameraAnim.setDuration(8000);
		mCameraAnim.setRepeatCount(Animation3D.INFINITE);
		mCameraAnim.setTransformable3D(mObjectGroup);
		
		
		
		
		mLightAnim = new RotateAroundAnimation3D(new Number3D(), Axis.Z, 10);
		mLightAnim.setDuration(3000);
		mLightAnim.setRepeatCount(Animation3D.INFINITE);
		mLightAnim.setTransformable3D(mLight);;
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
		mCameraAnim.start();
		mLightAnim.start();	
	}

	public void onDrawFrame(GL10 glUnused) {
		super.onDrawFrame(glUnused);
	}
}
