package mendel.vasilii.permutation.math

class GroupOfPermutation() : SetOfPermutation() {

    constructor(setPer: Collection<PerClass>):this(){
        permutations.addAll(setPer)
        makeGroup()
    }

    override fun add(per: PerClass) {
        super.add(per)
        makeGroup()
    }

    private fun makeGroup(){
        //делаем из множества группу
        //достаточно просто перемножить все что есть друг на друга
        val setPer = arrayListOf<PerClass>()
        for (p1 in permutations){
            for (p2 in permutations){
                val res = Operations.multiplication(p1, p2)
                if (!setPer.contains(res) && !contains(res)){
                    //тогда добавляем в множество
                    setPer.add(res)
                }
            }
        }
        if (setPer.size>0){
            permutations.addAll(setPer)
            makeGroup()
        }
    }
}