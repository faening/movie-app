package com.github.faening.movieapp.helper

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.TextAppearanceSpan
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import com.github.faening.movieapp.R

/**
 * Objeto auxiliar para adicionar a funcionalidade "Leia mais" e "Leia menos" aos TextViews.
 *
 * Nota: Nos testes realizados, a utilização de uma classe genérica para a funcionalidade "Leia mais" e "Leia menos" não foi possível porque
 * o evento de clique não era funcional, retornando sempre um erro do tipo `Clipboard overlay suppressed`. A solução encontrada no momento
 * foi a criação de um método auxiliar para cada funcionalidade.
 */
object ReadMoreLessHelper {

    /**
     * Adiciona a funcionalidade "Leia mais" a um TextView.
     *
     * @param context O contexto.
     * @param description O texto completo a ser exibido quando "Leia Mais" é clicado.
     * @param textView O TextView ao qual adicionar a funcionalidade.
     * @param maxLines O número máximo de linhas a serem exibidas inicialmente.
     * @param fadeIn Uma função para animar o esmaecimento de entrada do TextView.
     * @param fadeOut Uma função para animar o esmaecimento de saída do TextView.
     */
    fun addReadMoreText(
        context: Context,
        description: String,
        textView: TextView,
        maxLines: Int,
        fadeIn: (View, Float) -> Unit,
        fadeOut: (View, Float) -> Unit,
    ) {
        textView.text = description
        textView.post {
            if (textView.lineCount > maxLines) {
                val spannableDescription = createSpannableDescription(context, description, textView, maxLines, fadeIn, fadeOut)
                textView.maxLines = maxLines
                textView.text = spannableDescription
                textView.movementMethod = LinkMovementMethod.getInstance()
            }
        }
    }

    /**
     * Cria um `SpannableStringBuilder` com funcionalidade "Leia mais".
     *
     * @param context O contexto.
     * @param description O texto completo a ser exibido quando "Leia Mais" é clicado.
     * @param textView O TextView ao qual adicionar a funcionalidade.
     * @param maxLines O número máximo de linhas a serem exibidas inicialmente.
     * @param fadeIn Uma função para animar o esmaecimento de entrada do TextView.
     * @param fadeOut Uma função para animar o esmaecimento de saída do TextView.
     */
    private fun createSpannableDescription(
        context: Context,
        description: String,
        textView: TextView,
        maxLines: Int,
        fadeIn: (View, Float) -> Unit,
        fadeOut: (View, Float) -> Unit,
    ): SpannableStringBuilder {
        // Calcula o índice do último caractere visível
        val lastCharShown = textView.layout.getLineVisibleEnd(maxLines - 1)

        val readMoreText = "  ${context.resources.getString(R.string.read_more)}"
        val suffix = "  $readMoreText"

        // Calcula o índice no qual truncar o texto
        val cutOffIndex = if (lastCharShown - suffix.length - 3 > 0) lastCharShown - suffix.length - 3 else 0

        val actionDisplayText = "${description.substring(0, cutOffIndex)}...$suffix"
        val spannableDescription = SpannableStringBuilder(actionDisplayText)

        // Encontre o índice inicial do texto "Leia mais"
        val startIndex = actionDisplayText.indexOf(readMoreText)

        val textAppearanceSpan = createTextAppearanceSpan(context)
        val clickableSpan = createClickableSpan(context, description, textView, maxLines, spannableDescription, fadeIn, fadeOut)

        // Adiciona o `TextAppearanceSpan` ao `SpannableStringBuilder`
        spannableDescription.setSpan(
            textAppearanceSpan,
            startIndex,
            startIndex + readMoreText.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Adiciona o `ClickableSpan` ao `SpannableStringBuilder`
        spannableDescription.setSpan(
            clickableSpan,
            startIndex,
            startIndex + readMoreText.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return spannableDescription
    }

    /**
     * Cria um `ClickableSpan` com funcionalidade "Leia mais".
     *
     * @param context O contexto.
     * @param description O texto completo a ser exibido quando "Leia Mais" é clicado.
     * @param textView O TextView ao qual adicionar a funcionalidade.
     * @param maxLines O número máximo de linhas a serem exibidas inicialmente.
     * @param spannableDescription O `SpannableStringBuilder` a ser exibido quando "Ler menos" for clicado.
     * @param fadeIn Uma função para animar o esmaecimento de entrada do TextView.
     * @param fadeOut Uma função para animar o esmaecimento de saída do TextView.
     * @return Um `ClickableSpan`
     */
    private fun createClickableSpan(
        context: Context,
        description: String,
        textView: TextView,
        maxLines: Int,
        spannableDescription: SpannableStringBuilder,
        fadeIn: (View, Float) -> Unit,
        fadeOut: (View, Float) -> Unit,
    ): ClickableSpan {
        return object : ClickableSpan() {
            override fun onClick(widget: View) {
                val readMoreText = "  ${context.resources.getString(R.string.read_more)}"

                if (textView.text.toString().contains(readMoreText)) {
                    fadeOut(textView, 0.5f)
                    textView.maxLines = Integer.MAX_VALUE
                    textView.text = description
                    addReadLessText(context, description, textView, maxLines, fadeOut, fadeIn)
                    fadeIn(textView, 0.5f)
                } else {
                    fadeIn(textView, 0.5f)
                    textView.maxLines = maxLines
                    textView.text = spannableDescription
                    addReadMoreText(context, description, textView, maxLines, fadeOut, fadeIn)
                    fadeOut(textView, 0.5f)
                }
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)

                // Remove o sublinhado do texto "Leia mais"
                ds.isUnderlineText = false
            }
        }
    }

    /**
     * Cria um `TextAppearanceSpan` para o texto "Leia mais" e "Leia menos".
     *
     * @param context O contexto.
     * @return Um `TextAppearanceSpan`
     */
    private fun createTextAppearanceSpan(context: Context): TextAppearanceSpan {
        val typedValue = TypedValue()
        context.theme.resolveAttribute(R.style.CustomTextAppearance_TextBody_Link, typedValue, true)
        return TextAppearanceSpan(context, R.style.CustomTextAppearance_TextBody_Bold)
    }

    /**
     * Adiciona a funcionalidade "Leia menos" a um TextView.
     *
     * @param context O contexto.
     * @param description O texto completo a ser exibido quando "Leia Mais" é clicado.
     * @param textView O TextView ao qual adicionar a funcionalidade.
     * @param maxLines O número máximo de linhas a serem exibidas inicialmente.
     * @param fadeIn Uma função para animar o esmaecimento de entrada do TextView.
     * @param fadeOut Uma função para animar o esmaecimento de saída do TextView.
     */
    private fun addReadLessText(
        context: Context,
        description: String,
        textView: TextView,
        maxLines: Int,
        fadeIn: (View, Float) -> Unit,
        fadeOut: (View, Float) -> Unit
    ) {
        val readLessText = "  ${context.resources.getString(R.string.read_less)}"
        val actionDisplayText = "$description$readLessText"
        val spannableDescription = SpannableStringBuilder(actionDisplayText)

        // Encontre o índice inicial do texto "Leia menos"
        val startIndex = actionDisplayText.indexOf(readLessText)

        val textAppearanceSpan = createTextAppearanceSpan(context)
        val clickableSpan = createClickableSpan(context, description, textView, maxLines, spannableDescription, fadeIn, fadeOut)

        // Adiciona o `TextAppearanceSpan` ao `SpannableStringBuilder`
        spannableDescription.setSpan(
            textAppearanceSpan,
            startIndex,
            startIndex + readLessText.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Adiciona o `ClickableSpan` ao `SpannableStringBuilder`
        spannableDescription.setSpan(
            clickableSpan,
            startIndex,
            startIndex + readLessText.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        textView.text = spannableDescription

        // Habilita o clique em spans
        textView.movementMethod = LinkMovementMethod.getInstance()
    }

}