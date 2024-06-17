package com.example.kiosk.LV3

open class MenuItem_Lv3(val name: String, val description: String, val price: Double) {
    open fun printInfo() {
        println("$name | $description | W $price")
    }
}

open class Food_Lv3(name: String, description: String, price: Double) : MenuItem_Lv3(name, description, price)


open class Burger_Lv3(name: String, description: String, price: Double) : Food_Lv3(name, description, price)
class ShackBurger : Burger_Lv3("ShackBurger", "토마토, 양상추, 쉑소스가 토핑된 치즈버거", 6.9)
class SmokeShack : Burger_Lv3("SmokeShack", "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거", 8.9)
class ShroomBurger : Burger_Lv3("Shroom Burger", "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거", 9.4)
class Cheeseburger : Burger_Lv3("Cheeseburger", "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거", 6.9)
class Hamburger : Burger_Lv3("Hamburger", "비프패티를 기반으로 야채가 들어간 기본버거", 5.4)

open class FrozenCustard_Lv3(name: String, description: String, price: Double) : MenuItem_Lv3(name, description, price)
class Chocolate : FrozenCustard_Lv3("Chocolate", "초콜릿 아이스크림", 3.5)
class Strawberry : FrozenCustard_Lv3("Strawberry", "딸기 아이스크림", 3.5)
class Vanilla : FrozenCustard_Lv3("Vanilla", "바닐라 아이스크림", 3.5)

open class Drink_Lv3(name: String, description: String, price: Double) : MenuItem_Lv3(name, description, price)
class CocaCola : Drink_Lv3("Coca Cola", "코카콜라", 1.5)
class Sprite : Drink_Lv3("Sprite", "스프라이트", 1.5)
class Juice : Drink_Lv3("Juice", "상큼한 주스", 2.0)

open class Beer_Lv3(name: String, description: String, price: Double) : MenuItem_Lv3(name, description, price)
class Cass : Beer_Lv3("Cass", "카스", 5.0)
class Draft_Beer : Beer_Lv3("Draft Beer", "생맥주", 5.5)


class Menu_Lv3(val title: String, val items: List<MenuItem_Lv3>) {
    fun showMenu() {
        println("[ $title ]")
        for ((index, item) in items.withIndex()) {
            println("${index + 1}. ${item.name} | ${item.description} | W ${item.price}")
        }
        println("0. 뒤로가기 | 뒤로가기")
    }

    fun selectItem(index: Int): MenuItem_Lv3? {
        return if (index in 1..items.size) items[index - 1] else null
    }
}

class ShakeShackMenu_Lv3 {
    private val menus = mutableListOf<Menu_Lv3>()

    fun init() {
        val burgerMenu = Menu_Lv3("Burgers MENU", listOf(
            ShackBurger(), SmokeShack(), ShroomBurger(), Cheeseburger(), Hamburger()
        ))

        val custardMenu = Menu_Lv3("Frozen Custard MENU", listOf(
            Chocolate(), Strawberry(), Vanilla()
        ))

        val drinkMenu = Menu_Lv3("Drinks MENU", listOf(
            CocaCola(), Sprite(), Juice()
        ))

        val beerMenu = Menu_Lv3("Beer MENU", listOf(
            Cass(), Draft_Beer()
        ))

        menus.add(burgerMenu)
        menus.add(custardMenu)
        menus.add(drinkMenu)
        menus.add(beerMenu)
    }

    fun showMainMenu() {
        println("[ SHAKESHACK MENU ]")
        println("1. Burgers | 앵거스 비프 통살을 다져만든 버거")
        println("2. Frozen Custard | 매장에서 신선하게 만드는 아이스크림")
        println("3. Drinks | 매장에서 직접 만드는 음료")
        println("4. Beer | 뉴욕 브루클린 브루어리에서 양조한 맥주")
        println("0. 종료 | 프로그램 종료")
    }

    fun selectMainMenu(choice: Int): Menu_Lv3? {
        return if (choice in 1..menus.size) menus[choice - 1] else null
    }
}

fun main() {
    val shakeShackMenu = ShakeShackMenu_Lv3()
    shakeShackMenu.init()

    while (true) {
        shakeShackMenu?.showMainMenu()
        val mainChoice = readLine()?.toIntOrNull()

        if (mainChoice == 0) {
            println("프로그램을 종료합니다")
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
