package com.honyadew.saved.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.honyadew.designsystem.view.DcpAlertDialog
import com.honyadew.designsystem.view.DcpColorCard
import com.honyadew.saved.contract.SavedState
import com.honyadew.saved.model.SavedTabs
import com.honyadew.saved.view.part.SavedColorSchemeCard
import com.honyadew.designsystem.theme.colorSelect
import com.honyadew.designsystem.view.MiniColorCard
import com.honyadew.extencion.filterApply
import com.honyadew.extencion.string
import com.honyadew.model.ColorInfo
import com.honyadew.model.ColorSchemeFilters
import com.honyadew.model.CustomColorScheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SavedViewShow(
    state: SavedState.Show,
    onDeleteClick: (colorScheme: CustomColorScheme) -> Unit,
    onOpenColorScheme: (colorScheme: CustomColorScheme) -> Unit,
    onDismissOpen: () -> Unit,
    onChangeTab: (newTab: SavedTabs) -> Unit,
    onEditTitle: (newTitle: String, scheme: CustomColorScheme) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = SavedTabs.values().indexOf(state.selectedTab))

    LaunchedEffect(state.selectedTab){
        pagerState.scrollToPage(SavedTabs.values().indexOf(state.selectedTab))
    }

    LaunchedEffect(pagerState.currentPage){
        delay(600)
        onChangeTab.invoke(SavedTabs.values()[pagerState.currentPage])
    }

    Box(modifier = Modifier.fillMaxSize()){
        state.openedColorScheme?.let { colorScheme ->
            SavedDialog(colorScheme = colorScheme, onDismiss = onDismissOpen, onEditTitle = onEditTitle)
        }
        Column {
            SavedTabRow(
                selectedTab = SavedTabs.values()[pagerState.currentPage],
                onChangeTab = onChangeTab
            )
            SavedPager(
                colorSchemes = state.allSchemes,
                onDeleteClick = onDeleteClick,
                onOpenColorScheme = onOpenColorScheme,
                pagerState = pagerState,
                onChangeTab = onChangeTab

            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SavedPager(
    colorSchemes : List<CustomColorScheme>,
    pagerState: PagerState,
    onDeleteClick: (colorScheme: CustomColorScheme) -> Unit,
    onOpenColorScheme: (colorScheme: CustomColorScheme) -> Unit,
    onChangeTab: (newTab: SavedTabs) -> Unit,
    modifier: Modifier = Modifier,
) {
    HorizontalPager(
        pageCount = 3,
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) {page ->
        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 512.dp),modifier = Modifier.fillMaxSize()) {
            colorSchemes.filterApply(
                when(page){
                    0 -> ColorSchemeFilters.SingleColor
                    1 -> ColorSchemeFilters.UpToFourColors
                    else -> ColorSchemeFilters.MultiColors
                }
            ).forEach{colorScheme ->
                item {
                    if (colorScheme.colors.size == 1) {
                        DcpColorCard(
                            color = ColorInfo(
                                value = colorScheme.colors[0].value,
                                name = colorScheme.name
                            ),
                            onColorClick = {onOpenColorScheme.invoke(colorScheme)},
                            onDeleteColor = {onDeleteClick.invoke(colorScheme)},
                            modifier = Modifier.padding(8.dp)
                        )
                    } else {
                        SavedColorSchemeCard(
                            paletteInfo = colorScheme,
                            onPaletteClick = onOpenColorScheme,
                            onDeletePalette = onDeleteClick,
                            modifier = Modifier.padding(8.dp)

                        )
                    }
                }
            }
        }

    }
}

@Composable
private fun SavedDialog(
    colorScheme: CustomColorScheme,
    onDismiss: () -> Unit,
    onEditTitle: (newTitle: String, scheme: CustomColorScheme) -> Unit,
    modifier: Modifier = Modifier
) {
    DcpAlertDialog(
        title = colorScheme.name,
        onDismiss = onDismiss,
        onEditTitle = {onEditTitle(it, colorScheme)}
    ) {
        LazyVerticalGrid(columns = GridCells.Adaptive(minOf(96.dp))){
            colorScheme.colors.forEach { colorInfo ->
                item {
                    MiniColorCard(color = colorInfo, modifier = Modifier.padding(4.dp))
                }
            }
        }
    }
}

@Composable
private fun SavedTabRow(
    selectedTab: SavedTabs,
    onChangeTab: (newTab: SavedTabs) -> Unit,
    modifier: Modifier = Modifier
){
    TabRow(
        modifier = modifier,
        selectedTabIndex = SavedTabs.values().indexOf(selectedTab),
        containerColor = colorSelect(),
        contentColor = colorSelect(saturation = 90, inverse = true),
        indicator = {tabPositions ->
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(
                        tabPositions[SavedTabs
                            .values()
                            .indexOf(selectedTab)]
                    )
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(colorSelect(saturation = 90, inverse = true))
            )

        }
    ) {
        SavedTabs.values().forEach{ tab ->
            Tab(
                selected = selectedTab == tab,
                onClick = {onChangeTab.invoke(tab)},
                modifier = Modifier.defaultMinSize(minHeight = 48.dp)
            ) {
                val iconRes = if (selectedTab == tab){
                    tab.selectedTabId
                } else {
                    tab.unselectedTabId
                }
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = stringResource(id = tab.tabNameId)
                )
            }
        }
    }
}


@Preview
@Composable
private fun PreviewSavedViewShow(){
    SavedViewShow(
        state = SavedState.Show(
            allSchemes = listOf(
                CustomColorScheme(
                    colors = listOf(
                        ColorInfo(
                            value = Color.Green.string(),
                            name = "test color"
                        )
                    ),
                    name = "test color scheme"
                ),
                CustomColorScheme(
                    colors = listOf(
                        ColorInfo(
                            value = Color.Green.string(),
                            name = "test color"
                        ),
                        ColorInfo(
                            value = Color.Yellow.string(),
                            name = "test color"
                        ),
                        ColorInfo(
                            value = Color.Green.string(),
                            name = "test color"
                        ),
                    ),
                    name = "test color scheme2"
                ),
                CustomColorScheme(
                    colors = listOf(
                        ColorInfo(
                            value = Color.Green.string(),
                            name = "test color"
                        ),
                        ColorInfo(
                            value = Color.Yellow.string(),
                            name = "test color"
                        ),
                        ColorInfo(
                            value = Color.Green.string(),
                            name = "test color"
                        ),
                        ColorInfo(
                            value = Color.Yellow.string(),
                            name = "test color"
                        ),
                        ColorInfo(
                            value = Color.Green.string(),
                            name = "test color"
                        ),
                    ),
                    name = "test color scheme3"
                )
            ),
            selectedTab = SavedTabs.ONE_COLOR,
            openedColorScheme = CustomColorScheme(
                colors = listOf(
                    ColorInfo(value = Color.Green.string(), name = "test color"),
                    ColorInfo(
                        value = Color.Yellow.string(),
                        name = "test color"
                    ),
                    ColorInfo(value = Color.Green.string(), name = "test color"),
                    ColorInfo(
                        value = Color.Yellow.string(),
                        name = "test color"
                    ),
                    ColorInfo(value = Color.Green.string(), name = "test color"),
                ),
                name = "test color scheme3"
            )
        ),
        onDeleteClick = {},
        onOpenColorScheme = {},
        onDismissOpen = { },
        onChangeTab = {},
        onEditTitle = {it1,it2 ->}
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun TestPager(){
    val items = listOf("Item 1", "Item 2", "Item 3")

    val coroutine = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0)

    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
        ) {
            items.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutine.launch {
                            pagerState.scrollToPage(index)
                        }
                    }
                )
            }
        }

        HorizontalPager(
            pageCount = items.size,
            modifier = Modifier.fillMaxSize(),
            state = pagerState
        ) { page ->
            Text(
                text = items[page],
                fontSize = 24.sp,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}