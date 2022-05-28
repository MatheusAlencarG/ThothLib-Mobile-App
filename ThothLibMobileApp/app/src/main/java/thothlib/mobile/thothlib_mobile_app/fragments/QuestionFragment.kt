package thothlib.mobile.thothlib_mobile_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.*
import androidx.fragment.app.Fragment
import thothlib.mobile.thothlib_mobile_app.R

class QuestionFragment : Fragment() {

    lateinit var resposta_1: LinearLayout;
    lateinit var resposta_2: LinearLayout;
    lateinit var resposta_3: LinearLayout;
    lateinit var resposta_4: LinearLayout;
    lateinit var pergunta_1: LinearLayout;
    lateinit var pergunta_2: LinearLayout;
    lateinit var pergunta_3: LinearLayout;
    lateinit var pergunta_4: LinearLayout;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        resposta_1 = view.findViewById(R.id.resposta_1);
        resposta_2 = view.findViewById(R.id.resposta_2);
        resposta_3 = view.findViewById(R.id.resposta_3);
        resposta_4 = view.findViewById(R.id.resposta_4);
        pergunta_1 = view.findViewById(R.id.pergunta_1);
        pergunta_2 = view.findViewById(R.id.pergunta_2);
        pergunta_3 = view.findViewById(R.id.pergunta_3);
        pergunta_4 = view.findViewById(R.id.pergunta_4);

        pergunta_1.setOnClickListener {
            pergunta1_onclick()
        }

        pergunta_2.setOnClickListener {
            pergunta2_onclick()
        }

        pergunta_3.setOnClickListener {
            pergunta3_onclick()
        }

        pergunta_4.setOnClickListener {
            pergunta4_onclick()
        }

        return view
    }

    fun pergunta1_onclick() {
        val resposta1Param = resposta_1.layoutParams as ViewGroup.MarginLayoutParams;
        val pergunta2Param = pergunta_2.layoutParams as ViewGroup.MarginLayoutParams;
        val resposta2Param = resposta_2.layoutParams as ViewGroup.MarginLayoutParams;
        val pergunta3Param = pergunta_3.layoutParams as ViewGroup.MarginLayoutParams
        val pergunta4Param = pergunta_4.layoutParams as ViewGroup.MarginLayoutParams
        val resposta3Param = resposta_3.layoutParams as ViewGroup.MarginLayoutParams
        val resposta4Param = resposta_4.layoutParams as ViewGroup.MarginLayoutParams

        val top = pergunta_1.marginTop
        val bottom = pergunta_1.marginBottom
        val right = pergunta_1.marginRight
        val left = pergunta_1.marginLeft
        val top_2 = pergunta_2.marginTop
        val top_3 = pergunta_3.marginTop
        val top_4 = pergunta_4.marginTop

        if (!resposta_1.isVisible && !resposta_2.isVisible && !resposta_3.isVisible && !resposta_4.isVisible){
            resposta1Param.setMargins(left, top+195, right, bottom);
            pergunta2Param.setMargins(left, top_2+195, right, bottom);
            pergunta3Param.setMargins(left, top_3+195, right, bottom);
            pergunta4Param.setMargins(left, top_4+195, right, bottom);

            resposta_1.layoutParams = resposta1Param
            pergunta_2.layoutParams = pergunta2Param
            pergunta_3.layoutParams = pergunta3Param
            pergunta_4.layoutParams = pergunta4Param
            resposta_1.isVisible = true
        }
        else if(!resposta_1.isVisible && resposta_2.isVisible && !resposta_3.isVisible && !resposta_4.isVisible){
            resposta1Param.setMargins(left, top+195, right, bottom);
            pergunta2Param.setMargins(left, top_2+195, right, bottom);
            resposta2Param.setMargins(left, top_2+390, right, bottom);
            pergunta3Param.setMargins(left, top_3+195, right, bottom);
            pergunta4Param.setMargins(left, top_4+195, right, bottom);

            resposta_1.layoutParams = resposta1Param
            pergunta_2.layoutParams = pergunta2Param
            pergunta_3.layoutParams = pergunta3Param
            pergunta_4.layoutParams = pergunta4Param
            resposta_2.layoutParams = resposta2Param
            resposta_1.isVisible = true
        }
        else if(!resposta_1.isVisible && !resposta_2.isVisible && resposta_3.isVisible && !resposta_4.isVisible){
            resposta1Param.setMargins(left, top+195, right, bottom);
            pergunta2Param.setMargins(left, top_2+195, right, bottom);
            pergunta3Param.setMargins(left, top_3+195, right, bottom);
            pergunta4Param.setMargins(left, top_4+195, right, bottom);
            resposta3Param.setMargins(left, top_3+390, right, bottom);

            resposta_1.layoutParams = resposta1Param
            pergunta_2.layoutParams = pergunta2Param
            pergunta_3.layoutParams = pergunta3Param
            pergunta_4.layoutParams = pergunta4Param
            resposta_3.layoutParams = resposta3Param
            resposta_1.isVisible = true
        }else if(!resposta_1.isVisible && !resposta_2.isVisible && !resposta_3.isVisible && resposta_4.isVisible){
            resposta1Param.setMargins(left, top+195, right, bottom);
            pergunta2Param.setMargins(left, top_2+195, right, bottom);
            pergunta3Param.setMargins(left, top_3+195, right, bottom);
            pergunta4Param.setMargins(left, top_4+195, right, bottom);
            resposta4Param.setMargins(left, top_4+400, right, bottom);

            resposta_1.layoutParams = resposta1Param
            pergunta_2.layoutParams = pergunta2Param
            pergunta_3.layoutParams = pergunta3Param
            pergunta_4.layoutParams = pergunta4Param
            resposta_4.layoutParams = resposta4Param
            resposta_1.isVisible = true
        }
        else if(!resposta_1.isVisible && resposta_2.isVisible && resposta_3.isVisible && !resposta_4.isVisible){
            resposta1Param.setMargins(left, top+195, right, bottom);
            pergunta2Param.setMargins(left, top_2+195, right, bottom);
            resposta2Param.setMargins(left, top_2+390, right, bottom);
            pergunta3Param.setMargins(left, top_3+195, right, bottom);
            resposta3Param.setMargins(left, top_3+390, right, bottom);

            pergunta4Param.setMargins(left, top_4+195, right, bottom);

            resposta_1.layoutParams = resposta1Param
            pergunta_2.layoutParams = pergunta2Param
            resposta_2.layoutParams = resposta2Param
            pergunta_3.layoutParams = pergunta3Param
            pergunta_4.layoutParams = pergunta4Param
            resposta_3.layoutParams = resposta3Param
            resposta_1.isVisible = true
        }
        else if(!resposta_1.isVisible && resposta_2.isVisible && !resposta_3.isVisible && resposta_4.isVisible){
            resposta1Param.setMargins(left, top+195, right, bottom);
            pergunta2Param.setMargins(left, top_2+195, right, bottom);
            pergunta3Param.setMargins(left, top_3+195, right, bottom);
            resposta2Param.setMargins(left, top_2+390, right, bottom);
            resposta4Param.setMargins(left, top_4+390, right, bottom);

            pergunta4Param.setMargins(left, top_4+195, right, bottom);

            resposta_1.layoutParams = resposta1Param
            pergunta_2.layoutParams = pergunta2Param
            resposta_2.layoutParams = resposta2Param
            pergunta_3.layoutParams = pergunta3Param
            pergunta_4.layoutParams = pergunta4Param
            resposta_4.layoutParams = resposta4Param
            resposta_1.isVisible = true
        }else if(!resposta_1.isVisible && !resposta_2.isVisible && resposta_3.isVisible && resposta_4.isVisible){
            resposta1Param.setMargins(left, top+195, right, bottom);
            pergunta2Param.setMargins(left, top_2+195, right, bottom);
            pergunta3Param.setMargins(left, top_3+195, right, bottom);
            resposta4Param.setMargins(left, top_4+390, right, bottom);
            resposta3Param.setMargins(left, top_3+390, right, bottom);

            pergunta4Param.setMargins(left, top_4+195, right, bottom);

            resposta_1.layoutParams = resposta1Param
            pergunta_2.layoutParams = pergunta2Param
            resposta_3.layoutParams = resposta3Param
            pergunta_3.layoutParams = pergunta3Param
            pergunta_4.layoutParams = pergunta4Param
            resposta_4.layoutParams = resposta4Param
            resposta_1.isVisible = true
        } else if(!resposta_1.isVisible && resposta_2.isVisible && resposta_3.isVisible && resposta_4.isVisible){
            resposta1Param.setMargins(left, top+195, right, bottom);
            pergunta2Param.setMargins(left, top_2+195, right, bottom);
            resposta2Param.setMargins(left, top_2+390, right, bottom);
            pergunta3Param.setMargins(left, top_3+195, right, bottom);
            resposta3Param.setMargins(left, top_3+390, right, bottom);
            pergunta4Param.setMargins(left, top_4+195, right, bottom);
            resposta4Param.setMargins(left, top_4+390, right, bottom);

            resposta_1.layoutParams = resposta1Param
            pergunta_2.layoutParams = pergunta2Param
            resposta_2.layoutParams = resposta2Param
            pergunta_3.layoutParams = pergunta3Param
            pergunta_4.layoutParams = pergunta4Param
            resposta_3.layoutParams = resposta3Param
            resposta_4.layoutParams = resposta4Param
            resposta_1.isVisible = true
        }
        else {

            resposta1Param.setMargins(0, 0, 0,0);
            pergunta2Param.setMargins(left, top_2-195, right, bottom);
            resposta2Param.setMargins(left, top_2, right, bottom);
            pergunta3Param.setMargins(left, top_3-195, right, bottom);
            resposta3Param.setMargins(left, top_3, right, bottom);
            pergunta4Param.setMargins(left,top_4-195,right,bottom)
            resposta4Param.setMargins(left,top_4,right,bottom)
            resposta_1.layoutParams = resposta1Param
            pergunta_2.layoutParams = pergunta2Param
            pergunta_3.layoutParams = pergunta3Param
            pergunta_4.layoutParams = pergunta4Param

            resposta_2.layoutParams = resposta2Param
            resposta_3.layoutParams = resposta3Param
            resposta_4.layoutParams = resposta4Param

            resposta_1.isVisible = false

        }
    }
    fun pergunta2_onclick() {
        val resposta1Param = resposta_1.layoutParams as ViewGroup.MarginLayoutParams;
        val resposta2Param = resposta_2.layoutParams as ViewGroup.MarginLayoutParams;
        val resposta3Param = resposta_3.layoutParams as ViewGroup.MarginLayoutParams;
        val resposta4Param = resposta_4.layoutParams as ViewGroup.MarginLayoutParams;
        val pergunta2Param = pergunta_2.layoutParams as ViewGroup.MarginLayoutParams;
        val pergunta3Param = pergunta_3.layoutParams as ViewGroup.MarginLayoutParams;
        val pergunta4Param = pergunta_4.layoutParams as ViewGroup.MarginLayoutParams;


        val top_2 = pergunta_2.marginTop
        val bottom_2 = pergunta_2.marginBottom
        val right_2 = pergunta_2.marginRight
        val left_2 = pergunta_2.marginLeft
        val top_3 = pergunta_3.marginTop
        val top_4 = pergunta_4.marginTop
        val bottom_3 = pergunta_3.marginBottom
        val right_3 = pergunta_3.marginRight
        val left_3 = pergunta_3.marginLeft

        if (resposta_2.isVisible == false && resposta_3.isVisible == false && resposta_4.isVisible == false) {
            resposta2Param.setMargins(left_2, top_2+195, right_2, bottom_2);
            pergunta3Param.setMargins(left_3, top_3+195, right_3, bottom_3);
            pergunta4Param.setMargins(left_3, top_4+195, right_3, bottom_3);
            pergunta_4.layoutParams = pergunta4Param
            resposta_2.layoutParams = resposta2Param
            pergunta_2.layoutParams = pergunta2Param
            pergunta_3.layoutParams = pergunta3Param

            resposta_2.isVisible = true

        }else if (resposta_2.isVisible == false && resposta_3.isVisible == true && resposta_4.isVisible == false) {
            resposta2Param.setMargins(left_2, top_2+195, right_2, bottom_2);
            pergunta3Param.setMargins(left_3, top_3+195, right_3, bottom_3);
            pergunta4Param.setMargins(left_3, top_4+195, right_3, bottom_3);

            resposta3Param.setMargins(left_3, top_3+390, right_3, bottom_3);
            pergunta_4.layoutParams = pergunta4Param
            resposta_2.layoutParams = resposta2Param
            resposta_3.layoutParams = resposta3Param
            pergunta_2.layoutParams = pergunta2Param
            pergunta_3.layoutParams = pergunta3Param

            resposta_2.isVisible = true

        }else if (resposta_2.isVisible == false && resposta_3.isVisible == false && resposta_4.isVisible == true) {
            pergunta3Param.setMargins(left_3, top_3+195, right_3, bottom_3);
            pergunta4Param.setMargins(left_3, top_4+195, right_3, bottom_3);

            resposta2Param.setMargins(left_2, top_2+195, right_2, bottom_2);
            resposta4Param.setMargins(left_3, top_4+390, right_3, bottom_3);
            pergunta_4.layoutParams = pergunta4Param
            resposta_2.layoutParams = resposta2Param
            resposta_4.layoutParams = resposta4Param
            pergunta_3.layoutParams = pergunta3Param

            resposta_2.isVisible = true

        }else if (resposta_2.isVisible == false && resposta_3.isVisible == true && resposta_4.isVisible == true) {
            resposta2Param.setMargins(left_2, top_2+195, right_2, bottom_2);
            pergunta3Param.setMargins(left_3, top_3+195, right_3, bottom_3);
            pergunta4Param.setMargins(left_3, top_4+195, right_3, bottom_3);
            resposta4Param.setMargins(left_3, top_4+390, right_3, bottom_3);

            resposta3Param.setMargins(left_3, top_3+390, right_3, bottom_3);
            resposta_2.layoutParams = resposta2Param
            resposta_3.layoutParams = resposta3Param
            resposta_4.layoutParams = resposta4Param
            pergunta_2.layoutParams = pergunta2Param
            pergunta_3.layoutParams = pergunta3Param
            pergunta_4.layoutParams = pergunta4Param

            resposta_2.isVisible = true

        }else {
            resposta2Param.setMargins(0, 0, 0,0);
            pergunta3Param.setMargins(left_3, top_3-195, right_3, bottom_3);
            pergunta4Param.setMargins(left_3, top_4-195, right_3, bottom_3);
            resposta3Param.setMargins(left_3, top_3, right_3, bottom_3);
            resposta4Param.setMargins(left_3, top_4, right_3, bottom_3);
            resposta_2.layoutParams = resposta2Param
            pergunta_3.layoutParams = pergunta3Param
            resposta_3.layoutParams = resposta3Param
            pergunta_4.layoutParams = pergunta4Param
            resposta_4.layoutParams = resposta4Param

            resposta_2.isVisible = false

        }
    }
    fun pergunta3_onclick() {
        val resposta3Param = resposta_3.layoutParams as ViewGroup.MarginLayoutParams;
        val resposta4Param = resposta_4.layoutParams as ViewGroup.MarginLayoutParams;
        val pergunta4Param = pergunta_4.layoutParams as ViewGroup.MarginLayoutParams;

        val top_3 = pergunta_3.marginTop
        val top_4 = pergunta_4.marginTop
        val bottom_3 = pergunta_3.marginBottom
        val right_3 = pergunta_3.marginRight
        val left_3 = pergunta_3.marginLeft

        if (!resposta_3.isVisible && !resposta_4.isVisible) {
            resposta3Param.setMargins(left_3, top_3 + 195, right_3, bottom_3);
            pergunta4Param.setMargins(left_3, top_4 + 195, right_3, bottom_3);
            resposta_3.layoutParams = resposta3Param
            pergunta_4.layoutParams = pergunta4Param
            resposta_3.isVisible = true

        } else if (!resposta_3.isVisible && resposta_4.isVisible) {
            resposta3Param.setMargins(left_3, top_3 + 195, right_3, bottom_3);
            pergunta4Param.setMargins(left_3, top_4 + 195, right_3, bottom_3);
            resposta4Param.setMargins(left_3, top_4 + 390, right_3, bottom_3);
            resposta_3.layoutParams = resposta3Param
            resposta_4.layoutParams = resposta4Param
            pergunta_4.layoutParams = pergunta4Param
            resposta_3.isVisible = true

        } else {
            resposta3Param.setMargins(0, 0, 0, 0);
            pergunta4Param.setMargins(left_3, top_4 - 195, right_3, bottom_3);
            resposta4Param.setMargins(left_3, top_4 , right_3, bottom_3);
            resposta_3.layoutParams = resposta3Param
            resposta_4.layoutParams = resposta4Param
            pergunta_4.layoutParams = pergunta4Param
            resposta_3.isVisible = false


        }
    }
    fun pergunta4_onclick() {

        val top_3 = pergunta_3.marginTop
        val top_4 = pergunta_4.marginTop
        val bottom_3 = pergunta_3.marginBottom
        val right_3 = pergunta_3.marginRight
        val left_3 = pergunta_3.marginLeft

        val resposta4Param = resposta_4.layoutParams as ViewGroup.MarginLayoutParams;
        val pergunta4Param = pergunta_4.layoutParams as ViewGroup.MarginLayoutParams;

        if (!resposta_4.isVisible) {
            resposta4Param.setMargins(left_3, top_4 + 195, right_3, bottom_3);
            pergunta_4.layoutParams = pergunta4Param
            resposta_4.layoutParams = resposta4Param
            resposta_4.isVisible = true

        }
        else{
            resposta_4.layoutParams = resposta4Param
            resposta_4.isVisible = false
        }
    }

}