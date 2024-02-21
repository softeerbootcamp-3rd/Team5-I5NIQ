import com.hyundai.myexperience.data.entity.program.ProgramCar

data class ProgramConfData(
    val detailDescription: String,
    val cars: List<ProgramCar>,
    val id: Int,
    val images: List<String>
)