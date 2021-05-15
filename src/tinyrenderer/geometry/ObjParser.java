package tinyrenderer.geometry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import tinyrenderer.core.Entity;
import tinyrenderer.math.Vector3;

public class ObjParser 
{
    private ObjParser() {}
    
    public static Entity LoadObj(String filename) throws Exception
    {
        File objFile = new File("res//obj//" + filename);
        FileReader reader = new FileReader(objFile);
        BufferedReader bufferedReader = new BufferedReader(reader);
        
        String line = null;
        String[] data = new String[4];
        
        ArrayList<Vector3> vertices = new ArrayList<Vector3>();
        ArrayList<Integer> indices = new ArrayList<Integer>();

        while((line = bufferedReader.readLine()) != null)
        {
            if(line.indexOf("v ", 0) != -1)
            {
                data = line.split(" ");

                float x = Float.parseFloat(data[1]);
                float y = Float.parseFloat(data[2]);
                float z = Float.parseFloat(data[3]);
                
                vertices.add(new Vector3(x, y, z));
            }
            else if(line.indexOf("f ", 0) != -1)
            {
                data = line.split(" ");

                int a = Integer.parseInt(data[1].split("/")[0]);
                int b = Integer.parseInt(data[2].split("/")[0]);
                int c = Integer.parseInt(data[3].split("/")[0]);

                indices.add(a);
                indices.add(b);
                indices.add(c);
            }
        }

        bufferedReader.close();
        reader.close();
        
        return new Entity("#", vertices, indices);
    }
}
