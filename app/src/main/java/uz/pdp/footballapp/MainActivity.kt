package uz.pdp.footballapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import uz.pdp.footballapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this){}
        val build= AdRequest.Builder().build()
        binding= ActivityMainBinding.inflate(layoutInflater)
        binding.adView.loadAd(build)
        setContentView(binding.root)
    }
}