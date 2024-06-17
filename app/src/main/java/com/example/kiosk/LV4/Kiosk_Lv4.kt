package com.example.kiosk.LV4

import android.view.SubMenu
import java.io.IOException
import java.lang.IllegalArgumentException

open class MenuItem_Lv4(val name: String, val description: String, val price: Double) {
    open fun printInfo() {
        println("$name | $description | W $price")
    }
}

open class Food_Lv4(name: String, description: String, price: Double) : MenuItem_Lv4(name, description, price)


open class Burger_Lv4(name: String, description: String, price: Double) : Food_Lv4(name, description, price)
class ShackBurger : Burger_Lv4("ShackBurger", "토마토, 양상추, 쉑소스가 토핑된 치즈버거", 6.9)
class SmokeShack : Burger_Lv4("SmokeShack", "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거", 8.9)
class ShroomBurger : Burger_Lv4("Shroom Burger", "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거", 9.4)
class Cheeseburger : Burger_Lv4("Cheeseburger", "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거", 6.9)
class Hamburger : Burger_Lv4("Hamburger", "비프패티를 기반으로 야채가 들어간 기본버거", 5.4)

open class FrozenCustard_Lv4(name: String, description: String, price: Double) : MenuItem_Lv4(name, description, price)
class Chocolate : FrozenCustard_Lv4("Chocolate", "초콜릿 아이스크림", 3.5)
class Strawberry : FrozenCustard_Lv4("Strawberry", "딸기 아이스크림", 3.5)
class Vanilla : FrozenCustard_Lv4("Vanilla", "바닐라 아이스크림", 3.5)

open class Drink_Lv4(name: String, description: String, price: Double) : MenuItem_Lv4(name, description, price)
class CocaCola : Drink_Lv4("Coca Cola", "코카콜라", 1.5)
class Sprite : Drink_Lv4("Sprite", "스프라이트", 1.5)
class Juice : Drink_Lv4("Juice", "상큼한 주스", 2.0)

open class Beer_Lv4(name: String, description: String, price: Double) : MenuItem_Lv4(name, description, price)
class Cass : Beer_Lv4("Cass", "카스", 5.0)
class Draft_Beer : Beer_Lv4("Draft Beer", "생맥주", 5.5)


class Menu_Lv4(val title: String, val items: List<MenuItem_Lv4>) {
    fun showMenu() {
        println("[ $title ]")
        for ((index, item) in items.withIndex()) {
            println("${index + 1}. ${item.name} | ${item.description} | W ${item.price}")
        }
        println("0. 뒤로가기 | 뒤로가기")
    }

    fun selectItem(index: Int): MenuItem_Lv4? {
        return if (index in 1..items.size) items[index - 1] else null
    }
}

class ShakeShackMenu_Lv4 {
    private val menus = mutableListOf<Menu_Lv4>()

    fun init() {
        val burgerMenu = Menu_Lv4("Burgers MENU", listOf(
            ShackBurger(), SmokeShack(), ShroomBurger(), Cheeseburger(), Hamburger()
        ))

        val custardMenu = Menu_Lv4("Frozen Custard MENU", listOf(
            Vanilla(), Chocolate(), Strawberry()
        ))

        val drinkMenu = Menu_Lv4("Drinks MENU", listOf(
            CocaCola(), Sprite(), Juice()
        ))

        val beerMenu = Menu_Lv4("Beer MENU", listOf(
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

    fun selectMainMenu(choice: Int): Menu_Lv4? {
        return if (choice in 1..menus.size) menus[choice - 1] else null
    }

    fun selectSubMenu(subMenu: Menu_Lv4?) {
        while (true) {
            subMenu?.showMenu()
            val subChoice = readLine()?.toIntOrNull()

            if (subChoice == 0) {
                break
            }

            val selectItem = subMenu?.selectItem(subChoice!!)
            if (selectItem == null) {
                println("잘못된 번호입니다. 다시 입력해주세요.")
                continue
            }

            println("${selectItem.name} | W ${selectItem.price} | ${selectItem.description}")
            println("위의 메뉴를 추가하시겠습니까?")
            println("1. 확인 | 0. 취소")

            val addMenuChoice = readLine()?.toIntOrNull()
            if (addMenuChoice == 1) {

            }
        }
    }

    fun showShopping_Back() {
        if ()
    }
}

fun main() {
    val shakeShackMenu = ShakeShackMenu_Lv4()
    shakeShackMenu.init()

    while (true) {
        shakeShackMenu.showMainMenu()
        val mainChoice = try {
            readLine()?.toIntOrNull() ?: throw IllegalArgumentException("번호를 입력해주세요.")
        } catch (e : IOException) {
            println(e.message)
            continue
        }

        if (mainChoice == 0) {
            println("프로그램을 종료합니다")
            break
        }

        val selectedMenu = shakeShackMenu.selectMainMenu(mainChoice!!)
        if (selectedMenu == null) {
            println("잘못된 번호입니다. 다시 입력해주세요.")
            continue
        }

        when (mainChoice) {
            1, 2, 3, 4 -> {
                val choiceMenu = shakeShackMenu.selectMainMenu(mainChoice)
                if (selectedMenu == null) {
                    println("잘못된 번호입니다. 다시 입력해주세요")
                    continue
                }
                shakeShackMenu.selectSubMenu(selectedMenu)
            }

            5 -> {
                println("이대로 주문하시겠습니까?")
                println("1. 주문 | 0. 돌아가기")

                val orderChoice = readLine()?.toIntOrNull()
                if (orderChoice == 1 ) {

                }
            }

            6 -> {


                println("잘못된 번호입니다. 다시 입력해주세요.")

            }
        }

    }
}
