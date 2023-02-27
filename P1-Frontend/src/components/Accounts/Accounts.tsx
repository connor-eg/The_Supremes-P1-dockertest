import "./Accounts.css"

const accounts = [
  { id: "checking", name: "Checking", description: "Balance $4.50" },
  { id: "savings", name: "Savings", description: "Balance $200" },
];

export default function Accounts() {
  return (
    <div>
      <section className="account-section">
        <h2 className="account-heading">Accounts </h2>
        <p className="account-description">Please Select an account </p>
        <fieldset className="">
          <legend className="hidden-visually">Bank account</legend>
            {accounts.map((account, accountIdx) => (
              <div key={accountIdx} className="account-text">
                <div className="">
                  <label
                    htmlFor={`account-${account.id}`}
                    className="account-heading"
                  >
                    {account.name}
                  </label>
                  <p
                    id={`account-${account.id}-description`}
                    className="account-text"
                  >
                    {account.description}
                  </p>
                </div>
                <div className="">
                  <button
                    type="button"
                    className="account-button"
                    style={{ backgroundColor: "var(--tertiary-clr)" }}
                  >
                    Income{" "}
                  </button>
                  <button
                    type="button"
                    className="account-button"
                    style={{ backgroundColor: "var(--tertiary-clr)" }}
                  >
                    Expenses{" "}
                  </button>
                </div>
              </div>
            ))}
          
          <button
            type="button"
            className="account-button"
            style={{ backgroundColor: "var(--tertiary-clr)" }}
          >
            Create Account{" "}
          </button>
        </fieldset>
      </section>
      
    </div>
  );
}
