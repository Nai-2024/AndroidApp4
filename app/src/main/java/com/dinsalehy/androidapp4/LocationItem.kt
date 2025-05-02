package com.dinsalehy.androidapp4

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource


data class LocationItem(
    val id: Int,
    val name: String,
    val imageResId: Int
)


@Composable
fun LocationListScreen() {
    val locations = listOf(
        LocationItem(1, "Pyramids of Giza", R.drawable.pyramids),
        LocationItem(2, "Luxor Temple", R.drawable.luxor_temple),
        LocationItem(3, "Karnak Temple", R.drawable.karnak_temple),
        LocationItem(4, "Valley of the Kings", R.drawable.valley_kings),
        LocationItem(5, "Abu Simbel", R.drawable.abu_simbel),
        LocationItem(6, "The Egyptian Museum", R.drawable.egyptian_museum),
        LocationItem(7, "Citadel of Saladin", R.drawable.citadel_saladin),
        LocationItem(8, "Temple of Hatshepsut", R.drawable.temple_hatshepsut),
        LocationItem(9, "Philae Temple", R.drawable.philae_temple),
        LocationItem(10, "Saqqara Step Pyramid", R.drawable.saqqara_pyramid)
    )

    Surface(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Text(
                    text = "Historic Places",
                    style = MaterialTheme.typography.headlineMedium,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            items(locations) { location ->
                LocationCard(locationItem = location)
            }
        }
    }
}

@Composable
fun LocationCard(locationItem: LocationItem) {
    val context = LocalContext.current
    val dbHelper = remember { LocationDatabaseHelper(context) }
    var showImage by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(16.dp)
        ) {
            Text(text = locationItem.name, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))


                if (showImage) {
                    Image(
                        painter = painterResource(id = locationItem.imageResId),
                        contentDescription = locationItem.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Button(
                        onClick = {
                            showImage = true
                            // Insert when clicked
                            dbHelper.insertVisitedPlace(locationItem.id, locationItem.name)
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .height(50.dp)
                            .fillMaxWidth()
                    ) {
                        Text("Reveal Image")
                    }
                }
            }
        }
    }





@Preview(showBackground = true)
@Composable
fun PreviewLocationListScreen() {
    MaterialTheme {
        LocationListScreen()
    }
}