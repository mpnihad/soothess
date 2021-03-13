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

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                // A surface container using the 'background' color from the theme
                MyAppLogin() {

                    startActivity(Intent(this, ContentActivity::class.java))
                }
            }
        }
    }
}

@Composable
fun MyAppLogin(onLoginClicked: () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (isSystemInDarkTheme()) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_dark_login),
                    contentDescription = "main_background",
                    modifier = Modifier.fillMaxSize(),
                )
            } else {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_light_login),
                    contentDescription = "main_background",
                    modifier = Modifier.fillMaxSize(),
                )
            }
            Column(

                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "LOG IN",
                    style = MaterialTheme.typography.h1.copy(color = MaterialTheme.colors.onBackground),

                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                        .paddingFromBaseline(top = 200.dp)
                        .padding(bottom = 8.dp)
                )
                Spacer(modifier = Modifier.padding(top = 32.dp))
                EditText("Email address")
                EditText("Password")

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                        .height(72.dp)
                        .clickable {
                            onLoginClicked()
                        }
                        .background(
                            color = MaterialTheme.colors.primary,
                            shape = RoundedCornerShape(10.dp)
                        ),
                ) {
                    Text(
                        "LOG IN",
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center),
                        style = MaterialTheme.typography.button.copy(
                            color = MaterialTheme.colors.onSecondary

                        )
                    )
                }

                val annotatedString = AnnotatedString.Builder("Don't have an account? Sign up")
                    .apply {
                        addStyle(
                            SpanStyle(
                                textDecoration = TextDecoration.Underline
                            ),
                            23, 30
                        )
                    }

                Text(
                    text = annotatedString.toAnnotatedString(),
                    style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.primary),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .paddingFromBaseline(top = 32.dp, bottom = 16.dp)
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally)

                )
            }
        }
    }
}

@Composable
fun EditText(hint: String) {
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

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTheme {
        MyAppLogin() {
        }
    }
}
