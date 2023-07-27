package com.honey.palette

import com.honyadew.model.ColorInfo
import com.honyadew.model.ColorOfMaterial
import com.honyadew.model.Palette
import com.honey.domain.repository.PaletteRepository
import com.honey.palette.palette.FlatUI
import com.honey.palette.palette.HTML
import com.honey.palette.palette.Material
import com.honey.palette.palette.Metro
import com.honey.palette.palette.Social

class PaletteRepositoryImpl : PaletteRepository {
    override fun getAllColors(): List<com.honyadew.model.ColorInfo> {
        return (Material.palette + FlatUI.palette + Social.palette + Metro.palette + HTML.palette)
    }

    override fun getColorsByPalette(palette: com.honyadew.model.Palette): List<com.honyadew.model.ColorInfo> {
        return when(palette){
            com.honyadew.model.Palette.Material(com.honyadew.model.ColorOfMaterial.RED) -> { Material.red }
            com.honyadew.model.Palette.Material(com.honyadew.model.ColorOfMaterial.PINK) -> { Material.pink }
            com.honyadew.model.Palette.Material(com.honyadew.model.ColorOfMaterial.PURPLE) -> { Material.purple }
            com.honyadew.model.Palette.Material(com.honyadew.model.ColorOfMaterial.DEEP_PURPLE) -> { Material.deepPurple }
            com.honyadew.model.Palette.Material(com.honyadew.model.ColorOfMaterial.INDIGO) -> { Material.indigo }
            com.honyadew.model.Palette.Material(com.honyadew.model.ColorOfMaterial.BLUE) -> { Material.blue }
            com.honyadew.model.Palette.Material(com.honyadew.model.ColorOfMaterial.LIGHT_BLUE) -> { Material.lightBlue }
            com.honyadew.model.Palette.Material(com.honyadew.model.ColorOfMaterial.CYAN) -> { Material.cyan }
            com.honyadew.model.Palette.Material(com.honyadew.model.ColorOfMaterial.TEAL) -> { Material.teal }
            com.honyadew.model.Palette.Material(com.honyadew.model.ColorOfMaterial.GREEN) -> { Material.green }
            com.honyadew.model.Palette.Material(com.honyadew.model.ColorOfMaterial.LIGHT_GREEN) -> { Material.lightGreen }
            com.honyadew.model.Palette.Material(com.honyadew.model.ColorOfMaterial.LIME) -> { Material.lime }
            com.honyadew.model.Palette.Material(com.honyadew.model.ColorOfMaterial.YELLOW) -> { Material.yellow }
            com.honyadew.model.Palette.Material(com.honyadew.model.ColorOfMaterial.AMBER) -> { Material.amber }
            com.honyadew.model.Palette.Material(com.honyadew.model.ColorOfMaterial.ORANGE) -> { Material.orange }
            com.honyadew.model.Palette.Material(com.honyadew.model.ColorOfMaterial.DEEP_ORANGE) -> { Material.deepOrange }
            com.honyadew.model.Palette.Material(com.honyadew.model.ColorOfMaterial.BROWN) -> { Material.brown }
            com.honyadew.model.Palette.Material(com.honyadew.model.ColorOfMaterial.GREY) -> { Material.grey }
            com.honyadew.model.Palette.Material(com.honyadew.model.ColorOfMaterial.BLUE_GRAY) -> { Material.blueGray }
            com.honyadew.model.Palette.FlatUI -> { FlatUI.palette }
            com.honyadew.model.Palette.Social -> { Social.palette }
            com.honyadew.model.Palette.Metro -> { Metro.palette }
            com.honyadew.model.Palette.HTML -> { HTML.palette }
            else -> {
                emptyList<com.honyadew.model.ColorInfo>()
            }
        }
    }
}