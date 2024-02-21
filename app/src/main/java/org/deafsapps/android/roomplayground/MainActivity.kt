package org.deafsapps.android.roomplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import kotlinx.coroutines.launch
import org.deafsapps.android.roomplayground.data.db.CreationDatabase
import org.deafsapps.android.roomplayground.data.db.entity.ComponentEntity
import org.deafsapps.android.roomplayground.data.db.entity.ComponentWithElements
import org.deafsapps.android.roomplayground.data.db.entity.CreationEntity
import org.deafsapps.android.roomplayground.data.db.entity.ElementEntity
import org.deafsapps.android.roomplayground.data.db.entity.ElementWithImages
import org.deafsapps.android.roomplayground.data.db.entity.ImageEntity
import org.deafsapps.android.roomplayground.ui.theme.RoomplaygroundTheme
import java.util.UUID
import kotlin.random.Random


class MainActivity : ComponentActivity() {

    private val db: CreationDatabase by lazy {
        Room.databaseBuilder(
            applicationContext, CreationDatabase::class.java, "creation-database"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomplaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    DatabaseOperations(db = db)
                }
            }
        }
    }
}

@Composable
fun DatabaseOperations(modifier: Modifier = Modifier, db: CreationDatabase? = null) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(onClick = {
            coroutineScope.launch {
                db?.creationDao()?.saveCreation(creation = getDummyCreation())?.let(::println)
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Save New Creation")
        }
        OutlinedButton(onClick = {
            coroutineScope.launch {
                db?.creationDao()?.updateCreation(creation = getUpdatedDummyCreation())
                    ?.let(::println)
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Update Existing Creation")
        }
        OutlinedButton(onClick = {
            coroutineScope.launch {
                db?.creationDao()?.saveComponent(component = getDummyComponent())?.let(::println)
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Save New Component")
        }
        OutlinedButton(onClick = {
            db?.runInTransaction {
                coroutineScope.launch {
                    val componentWithElements = getDummyComponentWithElements()
                    db.creationDao().saveComponent(component = componentWithElements.component)
                        .let(::println)
                    db.creationDao()
                        .saveElements(elements = componentWithElements.elementsWithImages.map { it.element })
                        .let(::println)
                    db.creationDao()
                        .saveImages(images = componentWithElements.elementsWithImages.flatMap { it.images })
                        .let(::println)
                }
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Save New Component With Elements And Images")
        }
        OutlinedButton(onClick = {
            coroutineScope.launch {
                db?.creationDao()?.getCreations()?.let(::println)
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Log Existing Creation")
        }
        OutlinedButton(onClick = {
            coroutineScope.launch {
                db?.creationDao()?.deleteCreation("creation-1")?.let(::println)
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Delete Existing Creation")
        }
    }
}

private fun getDummyCreation(): CreationEntity = CreationEntity(
    creationId = "creation-1", description = "Dummy creation to play with"
)

private fun getUpdatedDummyCreation(): CreationEntity {
    val dummyCreation = getDummyCreation()
    return dummyCreation.copy(description = "$dummyCreation --- ${Random.nextInt()}")
}

private fun getDummyComponentWithElements(): ComponentWithElements {
    val componentId = UUID.randomUUID().toString()
    return ComponentWithElements(
        component = getDummyComponent(componentId = componentId),
        elementsWithImages = getDummyElementsWithImages(componentId = componentId)
    )
}

private fun getDummyComponent(componentId: String = UUID.randomUUID().toString()): ComponentEntity =
    ComponentEntity(
        componentId = componentId,
        creationId = "creation-1",
        description = "A random component with Id = $componentId"
    )

private fun getDummyElementsWithImages(
    componentId: String = UUID.randomUUID().toString()
): List<ElementWithImages> = listOf(
    getDummyElementWithImages(componentId = componentId),
    getDummyElementWithImages(componentId = componentId),
    getDummyElementWithImages(componentId = componentId),
    getDummyElementWithImages(componentId = componentId),
)

private fun getDummyElementWithImages(
    componentId: String = UUID.randomUUID().toString()
): ElementWithImages {
    val elementId = UUID.randomUUID().toString()
    return ElementWithImages(
        element = getDummyElement(elementId = elementId, componentId = componentId),
        images = getDummyImages(elementId = elementId)
    )
}

private fun getDummyElement(
    elementId: String = UUID.randomUUID().toString(),
    componentId: String = UUID.randomUUID().toString()
): ElementEntity = ElementEntity(
    elementId = elementId,
    componentId = componentId,
    description = "A random element with Id = $elementId"
)

private fun getDummyImages(elementId: String = UUID.randomUUID().toString()): List<ImageEntity> =
    listOf(
        getDummyImage(elementId = elementId), getDummyImage(elementId = elementId)
    )

private fun getDummyImage(elementId: String): ImageEntity {
    val imageId = UUID.randomUUID().toString()
    return ImageEntity(
        imageId = UUID.randomUUID().toString(),
        elementId = elementId,
        description = "A rando image with ID = $imageId"
    )
}

@Preview(showBackground = true)
@Composable
fun DatabaseOperationsPreview() {
    RoomplaygroundTheme {
        DatabaseOperations()
    }
}