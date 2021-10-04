package com.mmb.ui_compose.component.dialogue

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.mmb.ui_compose.Layout

@Composable
fun RadioTextButton(
    title: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable { onClick.invoke() }
        .padding(vertical = Layout.gutter),
        horizontalArrangement = Arrangement.Start) {
        RadioButton(
            selected = isSelected,
            onClick = {},
            colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primary)
        )
        Spacer(modifier = Modifier.width(Layout.bodyMargin))
        Text(text = title, style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun RadioDialogue(
    title: String,
    contentList: List<String>,
    selectedItem: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    onItemSelected: (String) -> Unit,
) {
    Dialog(onDismissRequest) {
        Column(
            modifier = modifier
                .background(MaterialTheme.colors.surface)
                .padding(horizontal = Layout.bodyMargin, vertical = Layout.gutter)
        ) {
            Text(text = title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.width(Layout.gutter))
            contentList.forEach {
                val isSelected = it == selectedItem
                RadioTextButton(it, isSelected) {
                    onItemSelected(it)
                }
            }
        }
    }
}

@Composable
fun RadioDialogueRow(
    title: String,
    selectedItem: String,
    contentList: List<String>,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val (isExpanded, setExpanded) = remember { mutableStateOf(false) }
    Text(
        text = title,
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                setExpanded(!isExpanded)
            }
            .padding(vertical = Layout.bodyMargin, horizontal = Layout.gutter)
    )

    if (isExpanded) {
        RadioDialogue(
            title = title,
            contentList = contentList,
            selectedItem = selectedItem,
            onDismissRequest = { setExpanded(false) }
        ) { item ->
            onItemSelected(item)
        }
    }
    Divider()
}