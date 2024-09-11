class BankAccount:
    def __init__(self, account_balance, account_number):
        self.__account_balance = account_balance
        self.__account_number = account_number

    def deposit(self, amount):
        if amount < 0:
            return

        self.__account_balance += amount

    def withdraw(self, amount):
        if 0 < amount <= self.__account_balance:
            self.__account_balance -= amount

    def _calculate_interest(self, interest_rate):
        return interest_rate * self.__account_balance

    def get_interest(self, interest_rate):
        return self._calculate_interest(interest_rate)


bank = BankAccount(123, 223117596)
print(bank.get_interest(0.01))
