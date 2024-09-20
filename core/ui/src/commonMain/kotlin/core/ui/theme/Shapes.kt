package core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    small = ShapeDefaults.Small,
    // Eg. Card
    medium = RoundedCornerShape(size = 4.dp),
    // Eg. OutlinedTextField, Button
    large = RoundedCornerShape(size = 4.dp)
)
