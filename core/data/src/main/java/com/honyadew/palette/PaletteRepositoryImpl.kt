package com.honyadew.palette

import com.honyadew.model.ColorInfo
import com.honyadew.model.Palette
import com.honyadew.domain.repository.PaletteRepository
import com.honyadew.palette.palette.FlatUI
import com.honyadew.palette.palette.HTML
import com.honyadew.palette.palette.Material
import com.honyadew.palette.palette.Metro
import com.honyadew.palette.palette.Social

class PaletteRepositoryImpl : PaletteRepository {
    override fun getAllColors(): List<com.honyadew.model.ColorInfo> {
        return (Material.palette + FlatUI.palette + Social.palette + Metro.palette + HTML.palette)
    }

    override fun getColorsByPalette(palette: com.honyadew.model.Palette): List<com.honyadew.model.ColorInfo> {
        return when(palette){
            Palette.Material(com.honyadew.model.ColorOfMaterial.RED) -> { Material.red }
            Palette.Material(com.honyadew.model.ColorOfMaterial.PINK) -> { Material.pink }
            Palette.Material(com.honyadew.model.ColorOfMaterial.PURPLE) -> { Material.purple }
            Palette.Material(com.honyadew.model.ColorOfMaterial.DEEP_PURPLE) -> { Material.deepPurple }
            Palette.Material(com.honyadew.model.ColorOfMaterial.INDIGO) -> { Material.indigo }
            Palette.Material(com.honyadew.model.ColorOfMaterial.BLUE) -> { Material.blue }
            Palette.Material(com.honyadew.model.ColorOfMaterial.LIGHT_BLUE) -> { Material.lightBlue }
            Palette.Material(com.honyadew.model.ColorOfMaterial.CYAN) -> { Material.cyan }
            Palette.Material(com.honyadew.model.ColorOfMaterial.TEAL) -> { Material.teal }
            Palette.Material(com.honyadew.model.ColorOfMaterial.GREEN) -> { Material.green }
            Palette.Material(com.honyadew.model.ColorOfMaterial.LIGHT_GREEN) -> { Material.lightGreen }
            Palette.Material(com.honyadew.model.ColorOfMaterial.LIME) -> { Material.lime }
            Palette.Material(com.honyadew.model.ColorOfMaterial.YELLOW) -> { Material.yellow }
            Palette.Material(com.honyadew.model.ColorOfMaterial.AMBER) -> { Material.amber }
            Palette.Material(com.honyadew.model.ColorOfMaterial.ORANGE) -> { Material.orange }
            Palette.Material(com.honyadew.model.ColorOfMaterial.DEEP_ORANGE) -> { Material.deepOrange }
            Palette.Material(com.honyadew.model.ColorOfMaterial.BROWN) -> { Material.brown }
            Palette.Material(com.honyadew.model.ColorOfMaterial.GREY) -> { Material.grey }
            Palette.Material(com.honyadew.model.ColorOfMaterial.BLUE_GRAY) -> { Material.blueGray }
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