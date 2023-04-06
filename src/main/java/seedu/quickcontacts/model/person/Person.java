package seedu.quickcontacts.model.person;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import seedu.quickcontacts.logic.commands.Command;
import seedu.quickcontacts.logic.commands.exceptions.CommandException;
import seedu.quickcontacts.logic.parser.EditMeetingParser;
import seedu.quickcontacts.logic.parser.exceptions.ParseException;
import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        if (otherPerson.getPhone() == null) {
            if (getPhone() != null) {
                return false;
            }
        } else {
            if (!otherPerson.getPhone().equals(getPhone())) {
                return false;
            }
        }
        if (otherPerson.getEmail() == null) {
            if (getEmail() != null) {
                return false;
            }
        } else {
            if (!otherPerson.getEmail().equals(getEmail())) {
                return false;
            }
        }
        if (otherPerson.getAddress() == null) {
            if (getAddress() != null) {
                return false;
            }
        } else {
            if (!otherPerson.getAddress().equals(getAddress())) {
                return false;
            }
        }
        return otherPerson.getName().equals(getName())
                && otherPerson.getTags().equals(getTags());
    }

    /**
     * Confirms the change of a name in the system and updates the relevant meeting details in the model
     * according to the changes specified in the provided map.
     *
     * @param newName The new name to be confirmed.
     * @param map A {@link HashMap} containing the changes to be made to the meeting. The keys in the map represent the
     *            fields to be edited, and the values represent the new values for those fields.
     * @param model The {@link Model} representing the current state of the system.
     */
    public void confirmNameChange(Name newName, HashMap<String, String> map, Model model) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String argumentsEditMeeting = entry.getKey() + " " + entry.getValue();
            try {
                Command command2 = new EditMeetingParser().parse(argumentsEditMeeting);
                command2.execute(model);
            } catch (ParseException e) {
                //shouldnot calll we formatted string
            } catch (CommandException e) {
                //shouldnot calll we formatted string
            }
        }
    }

    /**
     * Confirms the deletion of a name in the system and updates the relevant meeting details in the model
     * according to the changes specified in the provided map.
     *
     * @param map A {@link HashMap} containing the changes to be made to the meeting. The keys in the map represent the
     *            fields to be edited, and the values represent the new values for those fields.
     * @param model The {@link Model} representing the current state of the system.
     */
    public void confirmNameDeleted(HashMap<String, String> map, Model model) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String argumentsEditMeeting = entry.getKey() + " " + entry.getValue();
            try {
                Command command2 = new EditMeetingParser().parse(argumentsEditMeeting);
                command2.execute(model);
            } catch (ParseException e) {
                //shouldnot calll we formatted string
            } catch (CommandException e) {
                //shouldnot calll we formatted string
            }
        }
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
