package tinyrenderer.core;

public interface IApplication 
{   
    /**
     * Setup is called upon intilization of the window just 
     * before any of the Update methods are called the first time.
     */
    public void Setup();

    /**
     * Update is called every frame.
     * Update is the mostly used to implement any kind of dynamic animation
     */
    public void Update();    
}
