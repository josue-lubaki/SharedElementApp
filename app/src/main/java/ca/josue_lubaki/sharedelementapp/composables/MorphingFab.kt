package ca.josue_lubaki.sharedelementapp.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp


data class User(val name: String)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MorphingFab(modifier: Modifier = Modifier) {
    val users = remember { mutableStateListOf<User>() }
    // 1
    var showDialog by remember { mutableStateOf(false) }
    // 2
    SharedTransitionLayout(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Box(modifier = modifier) {
            UserList(
                users = users,
                modifier = Modifier.fillMaxSize(),
            )
            // 3
            AnimatedContent(
                targetState = showDialog,
                modifier = modifier,
                label = "morphing_fab",
            ) { addingUser ->
                // 4
                if (addingUser) {
                    Box {
                        // 5
                        InputBox(
                            onAddUser = {
                                users.add(it)
                                showDialog = false
                            },
                            // 6
                            onCancel = { showDialog = false },
                            modifier = Modifier
                                .align(Alignment.Center)
                                .width(320.dp)
                                .shadow(elevation = 4.dp)
                                // 7
                                .sharedBounds(
                                    sharedContentState = rememberSharedContentState(key = "fab"),
                                    animatedVisibilityScope = this@AnimatedContent,
                                ),
                        )
                    }
                } else {
                    // 8
                    Box {
                        FloatingActionButton(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(all = 16.dp)
                                // 9
                                .sharedBounds(
                                    sharedContentState = rememberSharedContentState(key = "fab"),
                                    animatedVisibilityScope = this@AnimatedContent,
                                ),
                            onClick = {
                                // 10
                                showDialog = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InputBox(onAddUser: (User) -> Unit, onCancel: () -> Unit, modifier: Modifier) {
    // 1
    var text by remember { mutableStateOf("") }
    // 2
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        // 3
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
        )
        // 4
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(top = 16.dp)
        ) {
            // 5
            TextButton(onClick = onCancel) {
                Text("Cancel")
            }
            // 6
            Button(
                onClick = {
                    onAddUser(User(text))
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Add")
            }
        }
    }

}


@Composable
fun UserList(users: SnapshotStateList<User>, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(users) { user ->
            Text(text = user.name)
        }
    }
}