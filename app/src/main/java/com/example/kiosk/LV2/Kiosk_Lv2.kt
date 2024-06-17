package com.example.kiosk.LV2

open class MenuItem_Lv2(val name: String, val description: String, val price: Double) {
    fun printInfo() {
        println("$name | $description | W $price")
    }
}


class Burger_Lv2(name: String, description: String, price: Double) : MenuItem_Lv2(name, description, price)
class FrozenCustard_Lv2(name: String, description: String, price: Double) : MenuItem_Lv2(name, description, price)
class Drink_Lv2(name: String, description: String, price: Double) : MenuItem_Lv2(name, description, price)
class Beer_Lv2(name: String, description: String, price: Double) : MenuItem_Lv2(name, description, price)



class Menu_Lv2(val title: String) {
    private val items = mutableListOf<MenuItem_Lv2>()

    fun addItem(item: MenuItem_Lv2) {
        items.add(item)
    }

    fun showMenu() {
        println("[ $title ]")
        for ((index, item) in items.withIndex()) {
            println("${index + 1}. ${item.name} | ${item.description} | W ${item.price}")
        }
        println("0. 뒤로가기 | 뒤로가기")
    }

    fun selectItem(index: Int): MenuItem_Lv2? {
        return if (index in 1..items.size) items[index - 1] else null
    }
}

class ShakeShackMenu_Lv2 {
    private val menus = mutableMapOf<Int, Menu_Lv2>()

    init {
        val burgerMenu = Menu_Lv2("Burgers MENU")
        burgerMenu.addItem(Burger_Lv2("ShackBurger", "토마토, 양상추, 쉑소스가 토핑된 치즈버거", 6.9))
        burgerMenu.addItem(Burger_Lv2("SmokeShack", "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거", 8.9))
        burgerMenu.addItem(Burger_Lv2("Shroom Burger", "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거", 9.4))
        burgerMenu.addItem(Burger_Lv2("Cheeseburger", "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거", 6.9))
        burgerMenu.addItem(Burger_Lv2("Hamburger", "비프패티를 기반으로 야채가 들어간 기본버거", 5.4))

        val custardMenu = Menu_Lv2("Frozen Custard MENU")
        custardMenu.addItem(FrozenCustard_Lv2("Chocolate", "초콜릿 아이스크림", 3.5))
        custardMenu.addItem(FrozenCustard_Lv2("Strawberry", "딸기 아이스크림", 3.5))
        custardMenu.addItem(FrozenCustard_Lv2("Vanilla", "바닐라 아이스크림", 3.5))

        val drinkMenu = Menu_Lv2("Drinks MENU")
        drinkMenu.addItem(Drink_Lv2("Coca Cola", "코카콜라", 1.5))
        drinkMenu.addItem(Drink_Lv2("Sprite", "스프라이트", 1.5))
        drinkMenu.addItem(Drink_Lv2("Juice", "주스", 2.0))

        val beerMenu = Menu_Lv2("Beer MENU")
        beerMenu.addItem(Beer_Lv2("Cass", "카스", 5.0))
        beerMenu.addItem(Beer_Lv2("Draft Beer", "생맥주", 5.5))

        menus[1] = burgerMenu
        menus[2] = custardMenu
        menus[3] = drinkMenu
        menus[4] = beerMenu
    }

    fun showMainMenu() {
        println("[ SHAKESHACK MENU ]")
        println("1. Burgers | 앵거스 비프 통살을 다져만든 버거")
        println("2. Frozen Custard| 매장에서 신선하게 만드는 아이스크림")
        println("3. Drinks | 매장에서 직접 만드는 음료")
        println("4. Beer | 뉴욕 브루클린 브루어리에서 양조한 맥주")
        println("0. 종료 | 프로그램 종료")
    }

    fun selectMainMenu(choice: Int): Menu_Lv2? {
        return menus[choice]
    }
}

fun main() {
    val shakeShackMenu = ShakeShackMenu_Lv2()

    while (true) {
        shakeShackMenu?.showMainMenu()
        val mainChoice = readLine()?.toIntOrNull()

        if (mainChoice == 0) {
            println("프로그램을 종료합니다.")
            break
        }

        val selectedMenu = shakeShackMenu?.selectMainMenu(mainChoice!!)
        if (selectedMenu == null) {
            println("잘못된 번호입니다. 다시 입력해주세요.")
            continue
        }

        while (true) {
            selectedMenu?.showMenu()
            val subChoice = readLine()?.toIntOrNull()

            if (subChoice == 0) {
                break
            }

            val selectedItem = selectedMenu?.selectItem(subChoice!!)
            if (selectedItem == null) {
                println("잘못된 번호입니다. 다시 입력해주세요.")
                continue
            }

            selectedItem?.printInfo()
        }
    }
}
