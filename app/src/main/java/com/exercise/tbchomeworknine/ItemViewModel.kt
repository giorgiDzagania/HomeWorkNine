package com.exercise.tbchomeworknine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel: ViewModel() {
   private val _choices = MutableLiveData<List<Choice>>()
   val choices: LiveData<List<Choice>> get() = _choices

   private val _outputs = MutableLiveData<List<Output>>()
   val outputs: LiveData<List<Output>> get() = _outputs

   private val _selectedCategory = MutableLiveData<TypeOfItem>()
   val selectedCategory: LiveData<TypeOfItem> get() = _selectedCategory

   init {
      _choices.value = getChoices()
      _outputs.value = getOutputs()
   }

   private fun getOutputs(): List<Output> {
      val allOutPuts = mutableListOf<Output>()

      val first = Output(0,R.drawable.combined_drawable,"Black Jacket","$150",TypeOfItem.Party)
      val second = Output(1,R.drawable.combine_drawable_four,"Yellow Hood","$90",TypeOfItem.Camping)
      val third = Output(2,R.drawable.combine_drawable_three,"red oversize","$80",TypeOfItem.Catergory1)
      val four = Output(3,R.drawable.combine_drawable_four,"Jeans","$60",TypeOfItem.Party)
      val five = Output(4,R.drawable.combined_drawable,"Shoes","$305",TypeOfItem.Catergory1)
      val six = Output(5,R.drawable.combine_drawable_three,"Trousers","$120",TypeOfItem.Catergory3)
      val seven = Output(6,R.drawable.combine_drawable_four,"Hood","$110",TypeOfItem.Camping)
      val eight = Output(7,R.drawable.combine_drawable_three,"shoo","$120",TypeOfItem.Catergory2)
      val nine = Output(8,R.drawable.combined_drawable,"Jacket","$130",TypeOfItem.Catergory3)
      val ten = Output(9,R.drawable.combine_drawable_two,"Shorts","$50",TypeOfItem.Catergory3)

      allOutPuts.add(first)
      allOutPuts.add(second)
      allOutPuts.add(third)
      allOutPuts.add(four)
      allOutPuts.add(five)
      allOutPuts.add(six)
      allOutPuts.add(seven)
      allOutPuts.add(eight)
      allOutPuts.add(nine)
      allOutPuts.add(ten)
      return allOutPuts
   }

   fun getChoices():MutableList<Choice>{
      val allChoices = mutableListOf<Choice>()

      val choiceOne = Choice(0,0,"All",ItemType.DIFFERENT)
      val choiceTwo = Choice(1,R.drawable.party_image,"Party",ItemType.NORMAL)
      val choiceThree = Choice(2,R.drawable.camping_image,"Camping",ItemType.NORMAL)
      val choiceFour = Choice(3,R.drawable.ic_category,"Category1",ItemType.NORMAL)
      val choiceFive = Choice(4,R.drawable.ic_category,"Category2",ItemType.NORMAL)
      val choiceSix = Choice(5,R.drawable.ic_category,"Category3",ItemType.NORMAL)

      allChoices.add(choiceOne)
      allChoices.add(choiceTwo)
      allChoices.add(choiceThree)
      allChoices.add(choiceFour)
      allChoices.add(choiceFive)
      allChoices.add(choiceSix)
      return allChoices
   }

   fun filterOutputsByCategory(category: TypeOfItem) {
      _selectedCategory.value = category
      updateFilteredOutputs()
   }

   private fun updateFilteredOutputs() {
      val selectedCategory = _selectedCategory.value

      _outputs.value = if (selectedCategory == TypeOfItem.ALL) {
         getOutputs()
      } else {
         getOutputs().filter { it.categoryType == selectedCategory }
      }
   }

}