/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.BrowseTheme
import com.example.androiddevchallenge.model.DesignModel
import com.example.androiddevchallenge.model.datas
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage

class ContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MyContentApp(datas().browseTheme, datas().designModel, datas().designModel2, datas().browseTheme2)
                }
            }
        }
    }
}

@Composable
fun MyContentApp(
    browseTheme: MutableList<BrowseTheme>,
    designModel: MutableList<DesignModel>,
    designModel2: MutableList<DesignModel>,
    browseTheme2: MutableList<BrowseTheme>
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
    ) {

        SearchText("Search")
        Text(
            "FAVORITE COLLECTIONS",
            Modifier
                .paddingFromBaseline(top = 40.dp)
                .padding(start = 16.dp),
            style = MaterialTheme.typography.h2
        )
        FavoriteCollection(browseTheme)
        FavoriteCollection(browseTheme2)

        Box() {

            Column() {

                Text(
                    "ALIGN YOUR BODY",
                    Modifier
                        .paddingFromBaseline(top = 40.dp)
                        .padding(start = 16.dp),
                    style = MaterialTheme.typography.h2
                )

                AlignBody(designModel)
                Text(
                    "ALIGN YOUR MIND",
                    Modifier
                        .paddingFromBaseline(top = 40.dp)
                        .padding(start = 16.dp),
                    style = MaterialTheme.typography.h2
                )
                AlignBody(designModel2)
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.BottomCenter)
                    .height(56.dp)
                    .background(MaterialTheme.colors.background)
                    .fillMaxWidth()

            ) {
                Row() {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        contentDown(icon = R.drawable.ic_baseline_spa_24, name = "HOME")
                    }
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        contentDown(
                            icon = R.drawable.ic_baseline_account_circle_24,
                            name = "PROFILE"
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.BottomCenter)
                    .size(56.dp)

                    .fillMaxWidth()
            ) {

                Box(modifier = Modifier.clip(shape = CircleShape).background(color = MaterialTheme.colors.primary).size(56.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_play_arrow_24),
                    contentDescription = "Play",
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                        .size(24.dp),
                    tint = MaterialTheme.colors.onSecondary

                )
            }
        }
    }
}

@Composable
private fun AlignBody(designModel: MutableList<DesignModel>) {
    LazyRow(
        modifier = Modifier
            .padding(top = 8.dp),
    ) {
        itemsIndexed(designModel) { index: Int, item: DesignModel ->
            Column(
                Modifier
                    .padding(start = 8.dp)
                    .width(88.dp)
            ) {
                CoilImage(
                    data = item.imageUrl,
                    contentDescription = item.imageName,
                    contentScale = ContentScale.Crop,
                    fadeIn = true,
                    modifier = Modifier
                        .size(88.dp)
                        .clip(shape = CircleShape)

                )
                Text(
                    item.imageName,
                    Modifier
                        .paddingFromBaseline(top = 24.dp)
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.h3
                )
            }
        }
    }
}

@Composable
private fun FavoriteCollection(browseTheme: MutableList<BrowseTheme>) {
    LazyRow(

        Modifier.padding(top = 8.dp, start = 16.dp)

    ) {

        itemsIndexed(browseTheme) { index: Int, item: BrowseTheme ->

            Box(
                modifier = Modifier
                    .width(192.dp)
                    .height(56.dp)
                    .padding(start = 8.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
                    .background(color = MaterialTheme.colors.surface)
            ) {
                Row() {
                    CoilImage(
                        data = item.imageUrl,
                        contentDescription = item.imageName,
                        contentScale = ContentScale.Crop,
                        fadeIn = true,
                        modifier = Modifier
                            .size(56.dp)

                    )
                    Text(
                        item.imageName,
                        Modifier
                            .padding(start = 16.dp)
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.CenterStart),
                        style = MaterialTheme.typography.h3
                    )
                }
            }
        }
    }
}

@Composable
private fun contentDown(icon: Int, name: String) {
    Icon(
        imageVector = ImageVector.vectorResource(id = icon),
        contentDescription = name,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .size(24.dp),
        tint = if (name == "HOME") {
            MaterialTheme.colors.onBackground
        } else {
            MaterialTheme.colors.primaryVariant
        }
    )
    Text(
        name,
        style = MaterialTheme.typography.caption.copy(
            color = if (name == "HOME") {
                MaterialTheme.colors.onBackground
            } else {
                MaterialTheme.colors.primaryVariant
            }
        ),
        modifier = Modifier
            .fillMaxWidth()

            .wrapContentWidth(align = Alignment.CenterHorizontally)
    )
}

@Composable
fun SearchText(hint: String) {

    var inputValue = remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = inputValue.value,
        onValueChange = { changedValue ->
            inputValue.value = changedValue
        },
        placeholder = {
            Text(
                hint,
                style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_search_24),
                contentDescription = "Search",
                Modifier.size(18.dp)
            )
        },
        textStyle = MaterialTheme.typography.body1,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = MaterialTheme.colors.onSurface
        ),
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            .clip(shape = RoundedCornerShape(4.dp))
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {

    MyTheme {
        MyContentApp(datas().browseTheme, datas().designModel, datas().designModel2, datas().browseTheme2)
    }
}
