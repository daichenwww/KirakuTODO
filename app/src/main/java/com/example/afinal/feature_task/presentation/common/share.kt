package com.example.afinal.feature_task.presentation.common

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import com.example.afinal.R
import java.io.ByteArrayOutputStream

private var textToShare = "在KIRAKU TODO的幫助下，我完成了超多任務，快來跟著我們一起前進吧！owo\nhttps://play.google.com/store/apps/details?id=com.kirakutodo.android";
//gallery change to drawable picture?
fun shareImageText(context: Context){
    val contentUri = getContentUri(context)

    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "image/png"
    intent.putExtra(Intent.EXTRA_SUBJECT,"Subject Here")
    intent.putExtra(Intent.EXTRA_TEXT, textToShare)
    intent.putExtra(Intent.EXTRA_STREAM, contentUri)
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    context.startActivity(Intent.createChooser(intent, "Share Via"))
}
private fun getContentUri(context: Context): Uri?{

    val bitmap: Bitmap = BitmapFactory.decodeResource(context.getResources(),
        R.drawable.accumulate_share)

    val bytes = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes)
    val path = MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "Title", null)
    return Uri.parse(path.toString())
}

