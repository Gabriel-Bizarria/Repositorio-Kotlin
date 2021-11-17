package com.generation.jogardados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //Aqui declarei a variável antes da construção da activity, para que haja maior eficiência na
    //execução do app.
    lateinit var imagemDado: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Aqui eu atribuo o ID do drawable do dado, a variável criada anteriormente.
        imagemDado = findViewById(R.id.imagem_dado)

        //Declaração do botão, com base no id do 'button'
        val buttonDado: Button = findViewById(R.id.buttonJogar)

        //Chamada de método para conseguirmos registrar o clique do usuário
        buttonDado.setOnClickListener{
            jogarDado()
            Toast.makeText(this, "Botão clicado",
            Toast.LENGTH_SHORT).show()
        }
    }
    //Função que sorteia o número e atribue o valor sorteado a um drawable diferente,
    //sendo cada um, uma imagem que representa os lados do dado.
    private fun jogarDado(){

        val numSorteado = (1..6).random()
        val imagemMostrada = when(numSorteado){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        imagemDado.setImageResource(imagemMostrada)
    }
}