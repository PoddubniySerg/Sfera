package ru.zavod.feature_auth.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.absoluteValue

internal class PhoneMaskTransformation(
    val mask: String = MASK,
    private val maskChar: Char = CH
) : VisualTransformation {

    companion object {
        private const val CH = '_'
        private const val MASK = "(${CH}${CH}${CH}) ${CH}${CH}${CH} - ${CH}${CH} - ${CH}${CH}"
    }

    private val specialSymbolsIndices = mask.indices.filter { mask[it] != maskChar }

    override fun filter(text: AnnotatedString): TransformedText {
        val out = StringBuilder()
        var maskIndex = 0
        text.forEach { char ->
            while (specialSymbolsIndices.contains(element = maskIndex)) {
                out.append(mask[maskIndex])
                maskIndex++
            }
            out.append(char)
            maskIndex++
        }
        return TransformedText(
            text = AnnotatedString(text = out.toString()),
            offsetMapping = offsetTranslator()
        )
    }

    private fun offsetTranslator() = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val offsetValue = offset.absoluteValue
            if (offsetValue == 0) {
                return offsetValue
            }
            var numberOfHashtags = 0
            val masked = mask.takeWhile {
                if (it == maskChar) numberOfHashtags++
                numberOfHashtags < offsetValue
            }
            return masked.length + 1
        }

        override fun transformedToOriginal(offset: Int): Int {
            return mask.take(offset.absoluteValue).count { it == maskChar }
        }
    }
}