package tinyrenderer.core;

import tinyrenderer.Application;
import tinyrenderer.math.Vector3;
import tinyrenderer.math.Matrix4;

enum CameraMode
{
    PERSPECTIVE,
    ORTHOGRAPHIC
};

public class Camera 
{
    public Vector3 position;
    public Vector3 rotation;

    private float fov;
    private float near;
    private float far;

    public CameraMode cameraMode;

    public Camera()
    {
        this.position = new Vector3();

        this.fov = 60.0f;
        this.near = 0.1f;
        this.far = 100.0f;

        this.cameraMode = CameraMode.PERSPECTIVE;
    }
    public Camera(Vector3 position, float fov, float near, float far, CameraMode cameraMode)
    {
        this.position = position;
        
        this.fov = fov;
        this.near = near;
        this.far = far;

        this.cameraMode = cameraMode;
    }

    public Matrix4 GetViewMatrix()
    {
        return Matrix4.LookAt(this.position, Vector3.Add(this.position, Vector3.FRONT), Vector3.DOWN);
    }
    public Matrix4 GetProjection()
    {
        if(cameraMode == CameraMode.PERSPECTIVE)
            return Matrix4.Prespective
            (
                (float)Math.toRadians(this.fov),
                (float)(Application.GetFrameBuffer().GetTextureBuffer().getHeight() / Application.GetFrameBuffer().GetTextureBuffer().getHeight()), 
                this.near, 
                this.far
            );
        else if(cameraMode == CameraMode.ORTHOGRAPHIC)
            return Matrix4.Identity();
        else
            return Matrix4.Identity();
    }

    public float GetFov()
    {
        return this.fov;
    }
    public void SetFov(float value)
    {
        this.fov = value;
    }
    public float GetNearPlane()
    {
        return this.near;
    }
    public void SetNearPlane(float value)
    {
        this.near = value;
    }
    public float GetFarPlane()
    {
        return this.far;
    }
    public void SetFarPlane(float value)
    {
        this.far = value;
    }
}
