package androidsamples.java.dicegames

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import java.util.*

class WalletActivity : AppCompatActivity() {

    private var btnDie: Button? = null
    private var twoOrMoreDie: Button? = null
    private var balance: TextView? = null
    private var vm: WalletViewModel? = null

    companion object {
        private const val BALANCE_RESULT = 11
        private const val BALANCE_KEY = "balance_result_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        vm = ViewModelProvider(this)[WalletViewModel::class.java]
        val d: Die = Die6()
        vm!!.setDie(d)
        btnDie = findViewById(R.id.btn_die)
        twoOrMoreDie = findViewById(R.id.button)
        balance = findViewById(R.id.txt_balance)

        val act = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == BALANCE_RESULT) {
                val intent = result.data
                if (intent != null) {
                    val s = intent.getStringExtra(BALANCE_KEY)
                    balance?.text = s
                    vm!!.setBalance(s!!.toInt())
                }
            }
        }

        twoOrMoreDie?.setOnClickListener {
            val act2 = Intent(this, TwoOrMoreActivity::class.java)
            val bundle = Bundle()
            bundle.putString("bal", balance?.text.toString())
            act2.putExtras(bundle)
            act.launch(act2)
        }

        btnDie?.setOnClickListener {
            vm!!.setWinValue(6)
            vm!!.setIncrement(5)
            vm!!.rollDie()
            updateUI()
            if (vm!!.dieValue() == 6) Toast.makeText(this, R.string.congrats, Toast.LENGTH_SHORT)
                .show()
        }
        updateUI()
    }

    private fun updateUI() {
        btnDie!!.text = String.format(Locale.ENGLISH, "%d", vm!!.dieValue())
        balance!!.text = String.format(Locale.ENGLISH, "%d", vm!!.balance())
    }
}