package a.aa;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (! Python.isStarted()) Python.start(new AndroidPlatform(this));

        setContentView(R.layout.activity_main);
        View root = findViewById(R.id.main);
        TextView textview = findViewById(R.id.text);

        PyObject pyFile = Python.getInstance().getModule("hello");
        PyObject pyList = pyFile.callAttr("list");
        List<PyObject> list = pyList.asList();

        root.setOnClickListener(v -> {
            if (index >= list.size()) index = 0;
            textview.setText(list.get(index).toString());
            index++;
        });
        
    }
}