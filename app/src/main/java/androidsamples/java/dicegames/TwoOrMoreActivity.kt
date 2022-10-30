package androidsamples.java.dicegames

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class TwoOrMoreActivity : AppCompatActivity() {
    private var rb: RadioButton? = null
    private var balance: TextView? = null
    private var vm: TwoOrMoreViewModel? = null
    private var backBtn: Button? = null
    private var die1: TextView? = null
    private var die2: TextView? = null
    private var die3: TextView? = null
    private var die4: TextView? = null
    private var rg: RadioGroup? = null
    private var extra: Bundle? = null
    private var bal: String? = ""
    private var l: List<Int>? = null
    private var l1: List<Int>? = null
    private var goBtn: Button? = null
    private var infoBtn: Button? = null
    private var wagerField: EditText? = null

    private fun switchInfo() {
        val act = Intent(this, InfoActivity::class.java)
        startActivity(act)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_or_more)

        extra = intent.extras
        vm = ViewModelProvider(this)[TwoOrMoreViewModel::class.java]
        backBtn = findViewById(R.id.btn_back)
        goBtn = findViewById(R.id.go_button)
        infoBtn = findViewById(R.id.info_button)
        wagerField = findViewById(R.id.wager_field)
        die1 = findViewById(R.id.first_die)
        die2 = findViewById(R.id.second_die)
        die3 = findViewById(R.id.third_die)
        die4 = findViewById(R.id.fourth_die)
        rg = findViewById(R.id.radio_group)
        balance = findViewById(R.id.balance_text_view)

        if (extra != null) {
            bal = extra!!.getString("bal")
        }
        if (!vm!!.dataCheck()) {
            balance?.text = bal
            vm!!.setBalance(bal!!.toInt())
            val d1: Die = Die6()
            val d2: Die = Die6()
            val d3: Die = Die6()
            val d4: Die = Die6()
            vm!!.addDie(d1)
            vm!!.addDie(d2)
            vm!!.addDie(d3)
            vm!!.addDie(d4)
            vm!!.dataSet(true)
        } else {
            balance?.text = vm!!.balance().toString()
            l1 = vm!!.diceValues()
            die1?.text = (l1?.get(0).toString())
            die2?.text = (l1?.get(1).toString())
            die3?.text = (l1?.get(2).toString())
            die4?.text = (l1?.get(3).toString())
        }

        backBtn?.setOnClickListener {
            vm!!.dataSet(false)
            val act3 = Intent(this, WalletActivity::class.java)
            val bundle = Bundle()
            bundle.putString("balance", balance?.text.toString())
            act3.putExtras(bundle)
            setResult(101, act3)
            finish()
        }

        infoBtn?.setOnClickListener { switchInfo() }
        
        goBtn?.setOnClickListener(View.OnClickListener {
            val x: Int = try {
                wagerField?.text.toString().toInt()
            } catch (e: NumberFormatException) {
                Toast.makeText(applicationContext, "Invalid input!", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            vm!!.setWager(x)
            val y = rg?.checkedRadioButtonId
            if (y != -1) {
                rb = y?.let { it1 -> findViewById(it1) }
                when (rb?.text.toString()) {
                    "2 Alike" -> {
                        vm!!.setGameType(GameType.TWO_ALIKE)
                    }
                    "3 Alike" -> {
                        vm!!.setGameType(GameType.THREE_ALIKE)
                    }
                    "4 Alike" -> {
                        vm!!.setGameType(GameType.FOUR_ALIKE)
                    }
                }
            }
            val gameResult: GameResult = try {
                vm!!.play()
            } catch (e: IllegalStateException) {
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            l = vm!!.diceValues()
            die1?.text = l?.get(0).toString()
            die2?.text = l?.get(1).toString()
            die3?.text = (l?.get(2).toString())
            die4?.text = (l?.get(3).toString())
            balance?.text = (vm!!.balance().toString())
            if (gameResult == GameResult.WIN) {
                Toast.makeText(applicationContext, "Congratulations!", Toast.LENGTH_SHORT).show()
            } else if (gameResult == GameResult.LOSS) {
                Toast.makeText(applicationContext, "Oh no! Coins lost!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}