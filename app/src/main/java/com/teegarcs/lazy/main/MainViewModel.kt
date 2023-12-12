package com.teegarcs.lazy.main

import com.teegarcs.lazy.core.BaseViewModel
import com.teegarcs.lazy.main.items.ImageOnly
import com.teegarcs.lazy.main.items.ImageOnlyNoHeader
import com.teegarcs.lazy.main.items.ItemIntent
import com.teegarcs.lazy.main.items.TextImageLeft
import com.teegarcs.lazy.main.items.TextImageRight

class MainViewModel : BaseViewModel<MainState, MainSE, MainIntent>() {
    private val penguinImage = ImageOnly(
        imageUrl = "https://free-images.com/lg/4275/penguin_funny_blue_water.jpg",
        imageCD = "Penguin Image Only",
        groupTitle = "Penguin"
    )

    private val penguinLeft = TextImageLeft(
        imageUrl = "https://free-images.com/lg/4275/penguin_funny_blue_water.jpg",
        title = "Penguin Left",
        description = "A penguin walking around dancing. ",
        groupTitle = "Penguin"
    )

    private val penguinRight = TextImageRight(
        imageUrl = "https://free-images.com/lg/4275/penguin_funny_blue_water.jpg",
        title = "Penguin Right",
        description = "A penguin walking around dancing. ",
        groupTitle = "Penguin"
    )

    private val dogImage = ImageOnly(
        imageUrl = "https://free-images.com/md/b436/1yorkshire_terrier_171701_640.jpg",
        imageCD = "Dog Image Only",
        groupTitle = "Dog"
    )

    private val dogLeft = TextImageLeft(
        imageUrl = "https://free-images.com/md/b436/1yorkshire_terrier_171701_640.jpg",
        title = "Dog Left",
        description = "A dog laying down. ",
        groupTitle = "Dog"
    )

    private val dogRight = TextImageRight(
        imageUrl = "https://free-images.com/md/b436/1yorkshire_terrier_171701_640.jpg",
        title = "Dog Right",
        description = "A dog laying down. ",
        groupTitle = "Dog"
    )

    private val horseImage = ImageOnly(
        imageUrl = "https://free-images.com/md/6944/horse_animal_horse_head_0.jpg",
        imageCD = "Horse Image Only",
        groupTitle = "Horse"
    )

    private val horseLeft = TextImageLeft(
        imageUrl = "https://free-images.com/md/6944/horse_animal_horse_head_0.jpg",
        title = "Horse Left",
        description = "A horse trotting. ",
        groupTitle = "Horse"
    )

    private val horseRight = TextImageRight(
        imageUrl = "https://free-images.com/md/6944/horse_animal_horse_head_0.jpg",
        title = "Horse Right",
        description = "A horse trotting. ",
        groupTitle = "Horse"
    )

    private val randomPenguin = ImageOnly(
        imageUrl = "https://free-images.com/lg/4275/penguin_funny_blue_water.jpg",
        imageCD = "Penguin Image Only End of List",
        groupTitle = "Penguin"
    )

    private val catImageNoHeader = ImageOnlyNoHeader(
        imageUrl = "https://free-images.com/md/e396/cat_hangover_red_cute_51.jpg",
        imageCD = "Cat without a sticky header"
    )

    override fun buildInitialState() = MainState(
        lazyItems = listOf(
            penguinLeft,
            penguinImage,
            penguinRight,
            catImageNoHeader,
            dogRight,
            dogImage,
            dogLeft,
            horseImage,
            horseLeft,
            horseRight,
            randomPenguin
        )
    )

    override fun processIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.ItemSelected -> {
                processItemIntents(intent.intent)
            }
        }
    }

    private fun processItemIntents(intent: ItemIntent) {
        when (intent) {
            is ItemIntent.ItemClicked -> sendSideEffect(MainSE.MainMessage(intent.title))
            is ItemIntent.ItemHeader -> sendSideEffect(MainSE.MainMessage(intent.headerTitle))
        }
    }
}
