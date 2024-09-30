package com.example.dethilai

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import coil.compose.AsyncImage


class HOme : AppCompatActivity() {
    data class Flower(val id: String, var name: String, var price: String, val url: String)

    // tao danh sach du lieu
    val flowerList = listOf(
        Flower(
            "1",
            "hoa loa ken 1",
            "20.000 VND",
            "https://www.gardendesign.com/pictures/images/675x529Max/site_3/helianthus-yellow-flower-pixabay_11863.jpg"
        ),
        Flower(
            "2",
            "hoa loa ken 2",
            "20.000 VND",
            "https://www.gardendesign.com/pictures/images/675x529Max/site_3/helianthus-yellow-flower-pixabay_11863.jpg"
        ),
        Flower(
            "3",
            "hoa loa ken 3",
            "20.000 VND",
            "https://www.gardendesign.com/pictures/images/675x529Max/site_3/helianthus-yellow-flower-pixabay_11863.jpg"
        ),
        Flower(
            "4",
            "hoa loa ken 4",
            "20.000 VND",
            "https://upload.wikimedia.org/wikipedia/commons/4/43/Cute_dog.jpg"
        ),
        Flower(
            "5",
            "hoa loa ken 5",
            "20.000 VND",
            "https://upload.wikimedia.org/wikipedia/commons/4/43/Cute_dog.jpg"
        ),

        )

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var DanhSach by remember { mutableStateOf(flowerList.toMutableList()) }
            var selectedFlower by remember {
                mutableStateOf<Flower?>(null)
            }
            var update by remember {
                mutableStateOf<Flower?>(null)
            }
            var add by remember {
                mutableStateOf<Flower?>(null)
            }
            Column(modifier = Modifier.padding(10.dp)) {
                Button(onClick = {
                    add = Flower("", "", "", "") // Create a new empty Flower object
                }) {
                    Text(text = "Add")
                }

                LazyVerticalGrid(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxSize(),
                    columns = GridCells.Fixed(3)
                ) {
                    items(DanhSach.size) { viTri ->
                        val flower = DanhSach.get(viTri)
                        Column {
                            AsyncImage(
                                modifier = Modifier.size(90.dp, 150.dp),
                                contentScale = ContentScale.Crop,
                                model = flower.url, contentDescription = ""
                            )
                            Text(text = "${flower.id}")
                            Text(text = "${flower.name}")
                            Text(text = "${flower.price}")
                            Button(onClick = {
                                // 1 mo dialog
                                selectedFlower = flower
                                // 2 mo man hinh moi
                                // 3. hien thi thong tin bang Toast
                            }) {
                                Text(text = "Detail")
                            }
                            Button(onClick = {
                                DanhSach = DanhSach.toMutableList().apply { remove(flower) }
                            }) {
                                Text(text = "Delete")
                            }
                            Button(onClick = {
                                update = flower
                            }) {
                                Text(text = "Update")
                            }
                        }
                    }
                }
            }


            add?.let {
                var addName by remember { mutableStateOf("") }
                var addPrice by remember { mutableStateOf("") }
                // Add flower dialog
                AlertDialog(onDismissRequest = { add = null }) {
                    Column(
                        modifier = Modifier
                            .height(350.dp)
                            .width(300.dp)
                            .background(Color.White),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Add Flower", fontSize = 30.sp, fontWeight = FontWeight.W700)
                        Spacer(modifier = Modifier.height(10.dp))
                        TextField(
                            value = addName,
                            onValueChange = { addName = it }, // Update addName state
                            label = { Text("Name") }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        TextField(
                            value = addPrice,
                            onValueChange = { addPrice = it }, // Update addPrice state
                            label = { Text("Price") }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(onClick = {
                            // Validate user input (optional)
                            if (addName.isEmpty() || addPrice.isEmpty()) {


                            } else {
                                // Add flower to the list
                                val newFlower = Flower(
                                    "8",
                                    addName,
                                    addPrice,
                                    "https://www.gardendesign.com/pictures/images/675x529Max/site_3/helianthus-yellow-flower-pixabay_11863.jpg"
                                ) // Create new Flower object
                                DanhSach = DanhSach.toMutableList().apply { add(newFlower) }
                                add = null // Close dialog after adding
                            }
                        }) {
                            Text(text = "Add")
                        }
                    }
                }
            }
            update?.let {
                var updatedName by remember { mutableStateOf(it.name) }
                var updatedPrice by remember { mutableStateOf(it.price) }
                AlertDialog(onDismissRequest = { update = null }) {
                    Column(
                        modifier = Modifier
                            .height(350.dp)
                            .width(300.dp)
                            .background(Color.White),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Update Flower", fontSize = 30.sp, fontWeight = FontWeight.W700)
                        Spacer(modifier = Modifier.height(10.dp))
                        TextField(
                            value = updatedName,
                            onValueChange = { updatedName = it },
                            label = { Text("Name") }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        TextField(
                            value = updatedPrice,
                            onValueChange = { updatedPrice = it },
                            label = { Text("Price") }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(onClick = {
                            // Update flower in the list
                            val updatedFlower = it.copy(name = updatedName, price = updatedPrice)
                            val index = DanhSach.indexOf(it)
                            if (index != -1) {
                                DanhSach = DanhSach.toMutableList().apply {
                                    set(index, updatedFlower)
                                }
                            }
                            update = null // Close dialog after update
                        }) {
                            Text(text = "Update")
                        }
                    }
                }
            }
            selectedFlower?.let {
                // khai bao dialog
                AlertDialog(onDismissRequest = {
                    selectedFlower = null
                }) {
                    Column(
                        modifier = Modifier
                            .height(350.dp)
                            .width(300.dp)
                            .background(Color.White),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            modifier = Modifier.size(90.dp, 150.dp),
                            contentScale = ContentScale.Crop,
                            model = it.url, contentDescription = ""
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = "${it.name}", fontSize = 25.sp, fontWeight = FontWeight.W700)
                    }

                }
            }

        }
    }

}