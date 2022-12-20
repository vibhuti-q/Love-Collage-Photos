package com.lovephotos.collageeditor.model

import com.lovephotos.collageeditor.listener_tom.TomCustomConfirmationClickListener

data class CustomConfirmationDialogModel(
    var dialogIconJusi: Int = 0,
    var dialogTitletom: String,
    var dialogDescriptiontom: String,
    var dialogEndPointtom: String,
    var positiveButtonTextJusi: String = "Yes",
    var negativeButtonTextJusi: String = "No",
    var listenertom: TomCustomConfirmationClickListener?,
)
