package com.example.ed_currencyconverter.Utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TextSizeUtil(textType: String): TextUnit {

    val configuration = LocalConfiguration.current

    val width = configuration.screenWidthDp.dp
    val height = configuration.screenWidthDp.dp

    return when(textType) {
        "MAIN_TITLE" -> MAIN_TITLE(width, height)
        "SUB_TITLE" -> SUB_TITLE(width, height)
        "ITEM_TEXT_H1" -> ITEM_TEXT_H1(width, height)
        "ITEM_TEXT_H2" -> ITEM_TEXT_H2(width, height)
        "ITEM_TEXT_H3" -> ITEM_TEXT_H3(width, height)
        "ITEM_TEXT_H4" -> ITEM_TEXT_H4(width, height)
        "BOXPANEL_TEXT_H1" -> BOXPANEL_TEXT_H1(width, height)
        "BOXPANEL_TEXT_H2" -> BOXPANEL_TEXT_H2(width, height)
        "LABEL_TEXT_H1" -> LABEL_TEXT_H1(width, height)
        "LABEL_TEXT_H2" -> LABEL_TEXT_H2(width, height)
        "LABEL_TEXT_H3" -> LABEL_TEXT_H3(width, height)
        else -> 0.sp
    }


}


fun MAIN_TITLE(width: Dp, height: Dp): TextUnit {

    return when{
        (width <= 375.dp) ->  25.sp //SAMPLE
        (width > 375.dp && width <= 415.dp) -> 30.sp//SAMPLE
        (width > 415.dp) ->  40.sp //SAMPLE
        else -> 30.sp
    }
}

fun SUB_TITLE(width: Dp, height: Dp): TextUnit {

    return when{
        (width <= 375.dp) -> 19.sp
        (width > 375.dp && width <= 415.dp) -> 24.sp
        (width > 415.dp) ->  34.sp
        else ->  24.sp
    }
}

fun ITEM_TEXT_H1(width: Dp, height: Dp): TextUnit {

    return when{
        (width <= 375.dp) ->  15.sp
        (width > 375.dp && width <= 415.dp) -> 20.sp
        (width > 415.dp) ->  25.sp
        else -> 20.sp
    }
}

fun ITEM_TEXT_H2(width: Dp, height: Dp): TextUnit {

    return when{
        (width <= 375.dp) ->  11.sp
        (width > 375.dp && width <= 415.dp) -> 16.sp
        (width > 415.dp) -> 21.sp
        else -> 16.sp
    }
}

fun ITEM_TEXT_H3(width: Dp, height: Dp): TextUnit {

    return when{
        (width <= 375.dp) ->  10.sp
        (width > 375.dp && width <= 415.dp) -> 15.sp
        (width > 415.dp) ->  20.sp
        else ->15.sp
    }
}

fun ITEM_TEXT_H4(width: Dp, height: Dp): TextUnit {

    return when{
        (width <= 375.dp) ->  9.sp
        (width > 375.dp && width <= 415.dp) -> 14.sp
        (width > 415.dp) ->  19.sp
        else ->14.sp
    }
}

fun BOXPANEL_TEXT_H1(width: Dp, height: Dp): TextUnit {
    return when{
        (width <= 375.dp) ->  15.sp
        (width > 375.dp && width <= 415.dp) -> 20.sp
        (width > 415.dp) ->  25.sp
        else -> 20.sp
    }
}

fun BOXPANEL_TEXT_H2(width: Dp, height: Dp): TextUnit {

    return when{
        (width <= 375.dp) ->  9.sp
        (width > 375.dp && width <= 415.dp) -> 13.sp
        (width > 415.dp) ->  19.sp
        else -> 14.sp
    }
}

fun LABEL_TEXT_H1(width: Dp, height: Dp): TextUnit {

    return when{
        (width <= 375.dp) ->  43.sp
        (width > 375.dp && width <= 415.dp) -> 48.sp
        (width > 415.dp) ->  53.sp
        else ->48.sp
    }
}
fun LABEL_TEXT_H2(width: Dp, height: Dp): TextUnit {

    return when{
        (width <= 375.dp) ->  35.sp
        (width > 375.dp && width <= 415.dp) -> 40.sp
        (width > 415.dp) ->  45.sp
        else -> 40.sp
    }
}

fun LABEL_TEXT_H3(width: Dp, height: Dp): TextUnit {

    return when{
        (width <= 375.dp) ->  19.sp
        (width > 375.dp && width <= 415.dp) -> 24.sp
        (width > 415.dp) ->  29.sp
        else -> 24.sp
    }
}
