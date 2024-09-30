package com.example.textscanner

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class MainActivity : AppCompatActivity() {
    lateinit var result:EditText
    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()
        result=findViewById(R.id.extracted)
        val camera=findViewById<ImageView>(R.id.camera)
        val erase=findViewById<ImageView>(R.id.edit)
        val copy=findViewById<ImageView>(R.id.copy)
        camera.setOnClickListener{
            val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(intent.resolveActivity(packageManager)!=null){
                startActivityForResult(intent,123)
            }
            else{
                Toast.makeText(this,"Oops, Try Reclicking the Picture",Toast.LENGTH_SHORT).show()
            }
        }
        erase.setOnClickListener{
            result.setText("")
        }
        copy.setOnClickListener{
            val clipBoard=getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip= ClipData.newPlainText("label",result.text.toString())
            clipBoard.setPrimaryClip(clip)
            Toast.makeText(this,"Copied to Clipboard!",Toast.LENGTH_SHORT).show()
        }
    }
 override fun onActivityResult(requestcode:Int, resultCode:Int, data:Intent?){
           super.onActivityResult(requestcode,resultCode,data)
       if(requestcode==123 && resultCode== RESULT_OK){
           val extras=data?.extras
           val bitmap=extras?.get("data") as Bitmap
           detectTextUsingBitmap(bitmap)
       }
   }
    private fun detectTextUsingBitmap(bitmap:Bitmap){
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        val image = InputImage.fromBitmap(bitmap, 0)
         recognizer.process(image)
            .addOnSuccessListener { visionText ->
                  result.setText(visionText.text.toString())
            }
            .addOnFailureListener { e ->
                Toast.makeText(this,"Failed to recognise text:${e.message}",Toast.LENGTH_SHORT).show()
            }
    }
}