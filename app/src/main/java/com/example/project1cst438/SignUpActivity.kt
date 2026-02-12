package com.example.project1cst438

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.data.local.DatabaseProvider
import com.data.local.UserRepository
import com.example.project1cst438.ui.screens.SignUpViewModel
import com.example.project1cst438.ui.screens.SignUpViewModelFactory
import com.example.project1cst438.ui.theme.Project1CST438Theme

class SignUpActivity : ComponentActivity() {
//    Gets the SignUpViewModel that is tied to this activity
//    Gets room database instance, so we don't keep recreating db (singleton)
//    Creates repo which is the class that talks to Room through DAO
//    Finally, creating the SignUpViewModel
    private val viewModel: SignUpViewModel by viewModels {
        val db = DatabaseProvider.getDatabase(this);
        val repo = UserRepository(db.userDao());
        SignUpViewModelFactory(repo);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project1CST438Theme {
                SignUpScreen(
                    onBack = { finish() },
                    viewModelz = viewModel

                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onBack: () -> Unit,
    viewModelz: SignUpViewModel

) {
    val context = LocalContext.current
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Account") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Sign up",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = "Create an account to get started.",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    OutlinedTextField(
                        value = viewModelz.username,
                        onValueChange = viewModelz::onUserNameChange,
                        label = {Text("Name") } ,
                        singleLine = true,
                        isError = viewModelz.userNameError != null,
                        supportingText = { if (viewModelz.userNameError != null) Text(viewModelz.userNameError!!) },
                        modifier = Modifier.fillMaxWidth().testTag("name"),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )

                    OutlinedTextField(
                        value = viewModelz.password,
                        onValueChange = viewModelz::onPasswordChange,
                        label = { Text("Password") },
                        singleLine = true,
                        isError = viewModelz.passwordError != null,
                        supportingText = { if (viewModelz.passwordError != null) Text(viewModelz.passwordError!!) },
                        modifier = Modifier.fillMaxWidth().testTag("password"),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                    contentDescription = if (passwordVisible) "Hide password" else "Show password"
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        )
                    )

                    OutlinedTextField(
                        value = viewModelz.confirmPassword,
                        onValueChange = viewModelz::onConfirmPasswordChange,
                        label = { Text("Confirm Password") },
                        singleLine = true,
                        isError = viewModelz.confirmPaswordError != null,
                        supportingText = { if (viewModelz.confirmPaswordError != null) Text(viewModelz.confirmPaswordError!!) },
                        modifier = Modifier.fillMaxWidth().testTag("confirmPassword"),
                        visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                                Icon(
                                    imageVector = if (confirmPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                    contentDescription = if (confirmPasswordVisible) "Hide password" else "Show password"
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Button(
                        onClick = {
                          viewModelz.onSignUpClicked { success ->
                              if (success) {
                                  Toast.makeText(context, "Successfully created!", Toast.LENGTH_SHORT).show()
//                                  context.startActivity(Intent(context, MainActivity::class.java))
                              } else {
                                  Toast.makeText(context, "Username already exist!", Toast.LENGTH_SHORT).show()
                              }
                          }


                        },
                        modifier = Modifier.fillMaxWidth().testTag("button")
                    ) {
                        Text("Create account")
                    }

                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SignUpPreview() {
//    Project1CST438Theme {
//        SignUpScreen(onBack = {}, viewModelz = viewModel())
//    }
//}
