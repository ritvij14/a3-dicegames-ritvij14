package androidsamples.java.dicegames;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.Locale;

public class WalletActivity extends AppCompatActivity {
  Button btnDie;
  TextView txtBalance;
  WalletViewModel vm;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wallet);

    vm = new ViewModelProvider(this).get(WalletViewModel.class);

    btnDie = findViewById(R.id.btn_die);
    txtBalance = findViewById(R.id.txt_balance);

    btnDie.setOnClickListener(v -> {
      vm.rollDie();
      updateUI();
      if (vm.dieValue() == 6) Toast.makeText(this, R.string.congrats, Toast.LENGTH_SHORT).show();
    });

    updateUI();
  }

  void updateUI() {
    btnDie.setText(String.format(Locale.ENGLISH, "%d", vm.dieValue()));
    txtBalance.setText(String.format(Locale.ENGLISH, "%s %d", getString(R.string.coins), vm.balance()));
  }
}