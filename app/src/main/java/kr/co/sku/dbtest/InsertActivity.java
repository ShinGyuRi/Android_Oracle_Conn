package kr.co.sku.dbtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class InsertActivity extends Activity {
	
	EditText edit_deptno,edit_dname,edit_loc;
	Button btn_insert;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_layout);
        
        edit_deptno = (EditText)findViewById(R.id.edit_deptno);
        edit_dname = (EditText)findViewById(R.id.edit_dname);
        edit_loc = (EditText)findViewById(R.id.edit_loc);
        
        btn_insert = (Button)findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				String deptno = edit_deptno.getText().toString();
				String dname = edit_dname.getText().toString();
				String loc = edit_loc.getText().toString();
				String requestURL = "http://117.17.143.104:8090/99JSP_myEMP/insertdo2.jsp";
				InputStream is= requestGet(requestURL,deptno,dname,loc);
						
				finish();
			}
		});//end onClick()
        
    }//end onCreate
    
    public InputStream requestGet(String requestURL, String deptno, String dname, String loc) {
        
    	Log.i("xxx", "requestGet start");
    	try {
    		
    		//1.
        	HttpClient client = new DefaultHttpClient();
        	
        	//폼데이터 저장
        	List<NameValuePair> dataList = new ArrayList<NameValuePair>();
        	dataList.add(new BasicNameValuePair("deptno", deptno));
        	dataList.add(new BasicNameValuePair("dname", dname));
        	dataList.add(new BasicNameValuePair("loc", loc));
        	requestURL = requestURL + "?" + URLEncodedUtils.format(dataList, "UTF-8");
        	
        	HttpGet get = new HttpGet(requestURL);
        	
        	//2. 요청
        	HttpResponse response = client.execute(get);
    		
        	//3. 결과 받기
        	HttpEntity entity = response.getEntity();
        	InputStream is = entity.getContent();
        	return is;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }//end requestGet()
    
}//end class