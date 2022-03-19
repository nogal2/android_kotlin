package com.example.sample31binding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.Intents.Insert.NOTES
import android.view.View
import android.widget.Toast
import com.example.sample31binding.databinding.ActivityMainBinding
import java.io.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.read.setOnClickListener(this)
        binding.write.setOnClickListener(this)
        binding.clear.setOnClickListener(this)
    }

    override fun onClick(view: View?) {

        if(view?.id == R.id.read) { // 클릭한 버튼 id와 R.id.read 가 같다면
            var reader:BufferedReader? = null
            try{
                val `in`:InputStream? = openFileInput(NOTES)

                if(`in` != null) {
                    reader = BufferedReader(InputStreamReader(`in`))
                    var str: String? = null
                    val buf = StringBuffer()

                    while(reader.readLine().also{str=it} != null) {
                        buf.append("""$str""")
                    }
                    binding.editText.setText(buf.toString())

                }
            } catch (e:FileNotFoundException){}
            catch (e:Exception) {
                Toast.makeText(this,"예외:$e", Toast.LENGTH_SHORT).show()
            } finally {
                if(reader != null) try {
                    reader?.close()
                } catch (e:Exception){}
            }
        }
        else if (view?.id == R.id.write) {
            var out:OutputStreamWriter? =null

            try {
                out = OutputStreamWriter(openFileOutput(NOTES, MODE_PRIVATE))
                out.write(binding.editText.text.toString())

                Toast.makeText(this, "데이터저장", Toast.LENGTH_SHORT).show()
            }catch (e:Exception) {
                e.printStackTrace()
            }finally {
                if(out != null) try {
                    out.close()
                } catch (e:IOException) {}
            }
        }else if (view?.id == R.id.clear) {
            binding.editText.setText("")
        }

    }

}
