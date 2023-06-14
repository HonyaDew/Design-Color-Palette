package com.honey.palette

import com.honey.domain.model.ColorInfo
import com.honey.domain.model.ColorOfMaterial
import com.honey.domain.model.Palette
import com.honey.domain.repository.PaletteRepository
import com.honey.palette.palette.FlatUI
import com.honey.palette.palette.HTML
import com.honey.palette.palette.Material
import com.honey.palette.palette.Metro
import com.honey.palette.palette.Social

class PaletteRepositoryImpl : PaletteRepository {
    override fun getAllColors(): List<ColorInfo> {
        return (Material.palette + FlatUI.palette + Social.palette + Metro.palette + HTML.palette)
    }

    override fun getColorsByPalette(palette: Palette): List<ColorInfo> {
        return when(palette){
            Palette.Material(ColorOfMaterial.RED) -> { Material.red }
            Palette.Material(ColorOfMaterial.PINK) -> { Material.pink }
            Palette.Material(ColorOfMaterial.PURPLE) -> { Material.purple }
            Palette.Material(ColorOfMaterial.DEEP_PURPLE) -> { Material.deepPurple }
            Palette.Material(ColorOfMaterial.INDIGO) -> { Material.indigo }
            Palette.Material(ColorOfMaterial.BLUE) -> { Material.blue }
            Palette.Material(ColorOfMaterial.LIGHT_BLUE) -> { Material.lightBlue }
            Palette.Material(ColorOfMaterial.CYAN) -> { Material.cyan }
            Palette.Material(ColorOfMaterial.TEAL) -> { Material.teal }
            Palette.Material(ColorOfMaterial.GREEN) -> { Material.green }
            Palette.Material(ColorOfMaterial.LIGHT_GREEN) -> { Material.lightGreen }
            Palette.Material(ColorOfMaterial.LIME) -> { Material.lime }
            Palette.Material(ColorOfMaterial.YELLOW) -> { Material.yellow }
            Palette.Material(ColorOfMaterial.AMBER) -> { Material.amber }
            Palette.Material(ColorOfMaterial.ORANGE) -> { Material.orange }
            Palette.Material(ColorOfMaterial.DEEP_ORANGE) -> { Material.deepOrange }
            Palette.Material(ColorOfMaterial.BROWN) -> { Material.brown }
            Palette.Material(ColorOfMaterial.GREY) -> { Material.grey }
            Palette.Material(ColorOfMaterial.BLUE_GRAY) -> { Material.blueGray }
            Palette.FlatUI -> { FlatUI.palette }
            Palette.Social -> { Social.palette }
            Palette.Metro -> { Metro.palette }
            Palette.HTML -> { HTML.palette }
            else -> {
                emptyList<ColorInfo>()
            }
        }
    }
}