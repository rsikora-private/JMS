package pl.sikorasoftware;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by robertsikora on 21.07.2016.
 */
public class SerializableObject implements Serializable {

    private String name;

    public SerializableObject(String name){
        this.name = name;
    }

    public void method(){
        System.out.println("");
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SerializableObject{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
