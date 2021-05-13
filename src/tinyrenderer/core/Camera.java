package tinyrenderer.core;

import tinyrenderer.Application;
import tinyrenderer.math.Vector3;
import tinyrenderer.math.Vector4;
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

    public Vector3 direction;

    public CameraMode cameraMode;

    private float fov;
    private float near;
    private float far;

    private Vector3 front;

    public Camera()
    {
        this.position = new Vector3();
        this.rotation = new Vector3();

        this.direction = Vector3.FRONT;

        this.fov = 60.0f;
        this.near = 0.1f;
        this.far = 100.0f;

        this.cameraMode = CameraMode.PERSPECTIVE;

        this.front = Vector3.FRONT;
    }
    public Camera(Vector3 position, float fov, float near, float far, CameraMode cameraMode)
    {
        this.position = position;
        this.rotation = new Vector3();

        this.direction = Vector3.FRONT;

        this.fov = fov;
        this.near = near;
        this.far = far;

        this.cameraMode = cameraMode;

        this.front = Vector3.FRONT;
    }

    public Matrix4 GetViewMatrix()
    {
        Matrix4 pitch = Matrix4.Rotate(rotation.x, Vector3.RIGHT);
        Matrix4 yaw = Matrix4.Rotate(rotation.y, Vector3.UP);
        Matrix4 rotate = Matrix4.Mult(pitch, yaw);

        this.direction = Vector4.ToVector3(Matrix4.Mult(rotate, Vector4.toVector4(this.front)));

        return Matrix4.LookAt(this.position, Vector3.Add(this.position, this.direction), Vector3.DOWN);
    }
    public Matrix4 GetProjectionMatrix()
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
