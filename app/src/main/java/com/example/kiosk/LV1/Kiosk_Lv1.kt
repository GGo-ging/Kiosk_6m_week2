package com.example.kiosk.LV1


data class MenuItem_Lv1(val id: Int, val description: String)

fun main() {
    val mainMenu = mutableListOf(
        MenuItem_Lv1(1, "Burgers | 앵거스 비프 통살을 다져만든 버거"),
        MenuItem_Lv1(2, "Frozen Custard | 매장에서 신선하게 만드는 아이스크림"),
        MenuItem_Lv1(3, "Drinks | 매장에서 직접 만드는 음료"),
        MenuItem_Lv1(4, "Beer | 뉴욕 브루클린 브루어리에서 양조한 맥주"),
        MenuItem_Lv1(0, "종료 | 프로그램 종료")
    )

    val burgersMenu = mutableListOf(
        MenuItem_Lv1(1, "ShackBurger | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
        MenuItem_Lv1(2, "SmokeShack | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
        MenuItem_Lv1(3, "Shroom Burger | W 9.4 | 몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거"),
        MenuItem_Lv1(4, "Cheeseburger | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
        MenuItem_Lv1(5, "Hamburger | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거")
    )

    val frozenCustardMenu = mutableListOf(
        MenuItem_Lv1(1, "Chocolate | W 3.5 | 초콜릿 아이스크림"),
        MenuItem_Lv1(2, "Strawberry | W 3.5 | 딸기 아이스크림"),
        MenuItem_Lv1(3, "Vanilla | W 3.5 | 바닐라 아이스크림")
    )

    val drinksMenu = mutableListOf(
        MenuItem_Lv1(1, "Coca Cola | W 1.5 | 코카콜라"),
        MenuItem_Lv1(2, "Sprite | W 1.5 | 스프라이트"),
        MenuItem_Lv1(3, "Juice | W 2.0 | 주스")
    )

    val beerMenu = mutableListOf(
        MenuItem_Lv1(1, "Cass | W 5.0 | 카스"),
        MenuItem_Lv1(2, "Draft Beer | W 5.5 | 생맥주")
    )

    val menus = mapOf(
        1 to burgersMenu,
        2 to frozenCustardMenu,
        3 to drinksMenu,
        4 to beerMenu
    )

    fun showMenu(menu: List<MenuItem_Lv1>, title: String) {
        println("[ $title ]")
        menu.forEach { item ->
            println("${item.id}. ${item.description}")
        }
        println("0. 뒤로가기 | 뒤로가기")
    }

    while (true) {
        println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.")
        println("[ SHAKESHACK MENU ]")
        mainMenu.forEach { item ->
            println("${item.id}. ${item.description}")
        }
        println("0. 종료 | 프로그램 종료")

        val mainChoice = readLine()?.toIntOrNull()

        if (mainChoice == 0) {
            println("프로그램을 종료합니다.")
            break
        }

        if (mainChoice == null || mainMenu.none { it.id == mainChoice }) {
            println("잘못된 번호입니다. 다시 입력해주세요.")
            continue
        }

        while (true) {
            val subMenu = menus[mainChoice]
            val subMenuTitle = when (mainChoice) {
                1 -> "Burgers MENU"
                2 -> "Frozen Custard MENU"
                3 -> "Drinks MENU"
                4 -> "Beer MENU"
                else -> ""
            }

            if (subMenu != null) {
                showMenu(subMenu, subMenuTitle)

                val subChoice = readLine()?.toIntOrNull()

                if (subChoice == 0) {
                    break
                }

                if (subChoice == null || subMenu.none { it.id == subChoice }) {
                    println("잘못된 번호입니다. 다시 입력해주세요.")
                    continue
                }

                println("선택하신 메뉴: ${subMenu.first { it.id == subChoice }.description}")
            }
        }
    }
}