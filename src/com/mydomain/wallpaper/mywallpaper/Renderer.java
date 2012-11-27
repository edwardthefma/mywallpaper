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
private BaseObject3D mmiku;
private Animation3D mCameraAnim, mLightAnim;

public Renderer(Context context) {
super(context);
setFrameRate(30);
}

public void initScene() {
mLight = new PointLight();
mLight.setPosition(0, 0, -4);
mLight.setPower(3);
mCamera.setLookAt(0, 0, 0);
mCamera.setZ(-12);

mCameraAnim = new RotateAnimation3D(Axis.Y, 360);
mCameraAnim.setDuration(8000);
mCameraAnim.setRepeatCount(Animation3D.INFINITE);
mCameraAnim.setTransformable3D(mmiku);

mLightAnim = new RotateAroundAnimation3D(new Number3D(), Axis.Z, 10);
mLightAnim.setDuration(3000);
mLightAnim.setRepeatCount(Animation3D.INFINITE);
mLightAnim.setTransformable3D(mLight);

ObjParser objParser = new ObjParser(mContext.getResources(), mTextureManager, R.raw.miku);
objParser.parse();
mmiku = objParser.getParsedObject();
mmiku.addLight(mLight);
addChild(mmiku);
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