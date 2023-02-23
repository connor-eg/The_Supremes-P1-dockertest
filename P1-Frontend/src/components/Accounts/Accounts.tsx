const accounts = [
  { id: "checking", name: "Checking", description: "Balance $4.50" },
  { id: "savings", name: "Savings", description: "Balance $200" },
];

export default function Accounts() {
  return (
    <div>
      <h2 className="text-lg font-medium leading-6 text-gray-900">Accounts </h2>
      <p className="mt-1 text-sm text-gray-500">Please Select an account </p>
      <fieldset className="mt-2">
        <legend className="sr-only">Bank account</legend>
        <div className="divide-y divide-gray-200">
          {accounts.map((account, accountIdx) => (
            <div key={accountIdx} className="relative flex items-start py-4">
              <div className="min-w-0 flex-1 text-sm">
                <label
                  htmlFor={`account-${account.id}`}
                  className="font-medium text-gray-700"
                >
                  {account.name}
                </label>
                <p
                  id={`account-${account.id}-description`}
                  className="text-gray-500"
                >
                  {account.description}
                </p>
              </div>
              <div className="ml-3 flex h-5 items-center">
                <input
                  id={`account-${account.id}`}
                  aria-describedby={`account-${account.id}-description`}
                  name="account"
                  type="radio"
                  defaultChecked={account.id === "checking"}
                  className="h-4 w-4 border-gray-300 text-indigo-600 focus:ring-indigo-500"
                />
              </div>
            </div>
          ))}
        </div>
        <button
          type="button"
          className="block rounded-md bg-indigo-600 py-1.5 px-3 text-center text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
        >
          Submit{" "}
        </button>
      </fieldset>
    </div>
  );
}
