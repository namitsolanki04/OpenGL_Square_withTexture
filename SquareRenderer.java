package com.example.user.bouncysquare;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by user on 9/5/2015.
 */
public class SquareRenderer implements GLSurfaceView.Renderer {

    public SquareRenderer(boolean useTransclucentBackground,Context context)
    {
        mTranslucentBackground=useTransclucentBackground;
        mSquare=new Square();
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {


        int resid = R.drawable.hedly;
        mSquare.createTexture(gl,this.context,resid);
        gl.glDisable(GL10.GL_DITHER);

        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_FASTEST);
        if(mTranslucentBackground)
        {
            gl.glClearColor(0,0,0,0);
        }
        else
        {
            gl.glClearColor(1,1,1,1);
        }
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        gl.glViewport(0,0,width,height);

        float ratio = (float)width/(float)height;

        gl.glMatrixMode(GL10.GL_PROJECTION);

        gl.glLoadIdentity();

        gl.glFrustumf(-ratio,ratio,-1,1,1,10);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_MODELVIEW);

        gl.glLoadIdentity();

        gl.glTranslatef(0.0f,(float)Math.sin(mTransY),-3.0f);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        mSquare.draw(gl);

        mTransY+=0.075;

    }

    private boolean mTranslucentBackground;
    private Square mSquare;
    private float mTransY;
    private float mAngle;

    private Context context;

}
